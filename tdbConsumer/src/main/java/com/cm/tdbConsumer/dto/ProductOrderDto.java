package com.cm.tdbConsumer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto implements Serializable {
    private  Long productId;
    private  String productCode;
    private  String product;
    private  Integer quantity;
    private  String firstName;
    private  String lastName;
    private  String deliveryAddressOne;
    private  String deliveryAddressTwo;
    private  String deliveryCity;
    private  String deliveryPostCode;
    private  String deliveryCountry;
    private  String deliveryState;
    private  String deliveryCounty;
    private  String email;
    private  String mobileNumberAndCode;
    private  String notes;
    private  OrderStatus status;
    private  Boolean paymentConfirmed;
    private  String orderNumber;
    private  LocalDateTime createdAt;
    private  LocalDateTime updatedAt;
    private LocalDateTime dispatchedAt;
    private String stripeInvoiceId;
}