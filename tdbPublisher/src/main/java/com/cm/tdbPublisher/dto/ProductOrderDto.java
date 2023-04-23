package com.cm.tdbPublisher.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ProductOrderDto {
    private final Long ProductId;
    private final String productCode;
    private final String product;
    private final Integer quantity;
    private final String firstName;
    private final String lastName;
    private final String deliveryAddressOne;
    private final String deliveryAddressTwo;
    private final String deliveryCity;
    private final String deliveryPostCode;
    private final String deliveryCountry;
    private final String deliveryState;
    private final String deliveryCounty;
    private final String email;
    private final String mobileNumberAndCode;
    private final String notes;
    private final OrderStatus status;
    private final Boolean paymentConfirmed;
    private final String orderNumber;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime dispatchedAt;
    private final String stripeInvoiceId;
}
