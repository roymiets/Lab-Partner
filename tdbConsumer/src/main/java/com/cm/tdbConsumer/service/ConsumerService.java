package com.cm.tdbConsumer.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cm.tdbConsumer.client.LabPartnerClient;
import com.cm.tdbConsumer.dto.ProductOrderDto;
import com.cm.tdbConsumer.dto.OrderStatus;
import com.cm.tdbConsumer.model.ProductOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {
    private final com.cm.tdbConsumer.repo.productOrderRepository productOrderRepository;

    @Value("${app.config.message.queue.topic}")
    private String messageQueueTopic;
    private final AmazonSQS amazonSQSClient;

    private final LabPartnerClient labPartnerClient;

    @Scheduled(fixedRate = 5000)
    public void pollQueue() {
        log.info("Poll queue cron called");
        GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(messageQueueTopic);

        ReceiveMessageResult messageResult = amazonSQSClient.receiveMessage(queueUrl.getQueueUrl());
        if (!messageResult.getMessages().isEmpty()) {
            log.info("Fetched list from queue");
            messageResult.getMessages().parallelStream().forEach(msg -> {
                        processMessage(msg.getBody());
                        amazonSQSClient.deleteMessage(queueUrl.getQueueUrl(), msg.getReceiptHandle());
                    }
            );
        } else {
            log.info("Queue List is empty");
        }

    }

    public void processMessage(String msg) {
        log.info("Received message: " + msg);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ProductOrderDto orderDto = objectMapper.readValue(msg, ProductOrderDto.class);
            log.info("Received order: " + orderDto);
            ProductOrder productOrder = new ProductOrder();
            productOrder.setId(orderDto.getProductId());
            productOrder.setProductCode(orderDto.getProductCode());
            productOrder.setProduct(orderDto.getProduct());
            productOrder.setQuantity(orderDto.getQuantity());
            productOrder.setFirstName(orderDto.getFirstName());
            productOrder.setLastName(orderDto.getLastName());
            productOrder.setDeliveryAddressOne(orderDto.getDeliveryAddressOne());
            productOrder.setDeliveryAddressTwo(orderDto.getDeliveryAddressTwo());
            productOrder.setDeliveryCity(orderDto.getDeliveryCity());
            productOrder.setDeliveryPostCode(orderDto.getDeliveryPostCode());
            productOrder.setDeliveryCountry(orderDto.getDeliveryCountry());
            productOrder.setDeliveryState(orderDto.getDeliveryState());
            productOrder.setDeliveryCounty(orderDto.getDeliveryCounty());
            productOrder.setEmail(orderDto.getEmail());
            productOrder.setMobileNumberAndCode(orderDto.getMobileNumberAndCode());
            productOrder.setNotes(orderDto.getNotes());
            productOrder.setStatus(OrderStatus.PENDING_FULFIL);
            productOrder.setPaymentConfirmed(false);
            productOrder.setOrderNumber(orderDto.getOrderNumber());
            productOrder.setStripeInvoiceId("your-stripe-invoice-id");
            ProductOrder order=saveOrderToDatabase(productOrder);

           // labPartnerClient.placeOrder(orderDto); called lab api
            order.setStatus(OrderStatus.SENT_TO_LAB);
            saveOrderToDatabase(order);

        } catch (JsonProcessingException e) {
            log.error("Error processing message: " + e.getMessage());
        }
        log.info(msg);
    }

    public ProductOrder saveOrderToDatabase(ProductOrder order) {
        try {
            //Calling lab partner
           return productOrderRepository.save(order);
        } catch (Exception e) {
            log.error("Error saving order to database: " + e.getMessage());
            throw new RuntimeException("Error saving order to database");

        }
    }



}
