package com.cm.tdbPublisher.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.InvalidMessageContentsException;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.cm.tdbPublisher.dto.GenericResponse;
import com.cm.tdbPublisher.dto.ProductOrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublisherService {


    @Value("${app.config.message.queue.topic}")
    private String messageQueueTopic;
    private final AmazonSQS amazonSQSClient;

    public void sendOrderToQueue(ProductOrderDto orderDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String orderJson = objectMapper.writeValueAsString(orderDto);
            GetQueueUrlResult queueUrlResult = amazonSQSClient.getQueueUrl(messageQueueTopic);
            log.info("Reading SQS Queue done: URL {}", queueUrlResult.getQueueUrl());
            amazonSQSClient.sendMessage(queueUrlResult.getQueueUrl(),orderJson);
        } catch (QueueDoesNotExistException | InvalidMessageContentsException e) {
            log.error("Queue does not exist {}", e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public GenericResponse placeOrder(ProductOrderDto productOrderDto) {
        sendOrderToQueue(productOrderDto);
        GenericResponse response = new GenericResponse();
        response.msg = "Sent to queue";
        return response;
    }



}
