package com.cm.tdbWebhook.service;


import com.cm.tdbWebhook.repo.ProductRepository;
import com.cm.tdbWebhook.dto.ProductOrderDto;
import com.cm.tdbWebhook.model.ProductOrder;
import com.cm.tdbWebhook.repo.productOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderWebHookService {
    private final ProductRepository productRepository;
    private final productOrderRepository orderRepository;

    public String updateOrderStatus(ProductOrderDto productOrderDto) {
        //todo update db
        Optional<ProductOrder> productOptional = orderRepository.findById(productOrderDto.getId());

        if (productOptional.isPresent()) {
            ProductOrder product= productOptional.get();

            // Update the product's order status
            product.setStatus(productOrderDto.getStatus());

            // Save the updated product to the database
            orderRepository.save(product);

        }
        else{
            return "order id is not exist";
        }
         return "order status updated";
    }
}
