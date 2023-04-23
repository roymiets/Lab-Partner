package com.cm.tdbConsumer.dto;

public enum OrderStatus {
    CREATED("created"),
    CONFIRMED("confirmed"),
    CANCELLED("cancelled"),
    DISPATCHED("dispatched"),
    PENDING_FULFIL("pending_order"),
    SENT_TO_LAB("sendToLab"),
    REFUNDED("refunded");

    private final String columnName;

    OrderStatus(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
