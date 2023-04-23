package com.cm.tdbWebhook.dto;

public enum OrderStatus {
    CREATED("created"),
    CONFIRMED("confirmed"),
    CANCELLED("cancelled"),
    DISPATCHED("dispatched"),
    ORDER_SHIPPED("order shipped"),
    SAMPLE_RECEIVED("smaple received"),
    RESULT_READY("result ready"),
    CUSTOMER_RECEIVED("customer received"),
    REFUNDED("refunded");

    private final String columnName;

    OrderStatus(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
