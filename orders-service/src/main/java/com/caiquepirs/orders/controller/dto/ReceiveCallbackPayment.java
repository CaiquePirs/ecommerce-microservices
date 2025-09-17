package com.caiquepirs.orders.controller.dto;

public record ReceiveCallbackPayment(
        Long orderId,
        String paymentKey,
        String notes,
        boolean status) {
}
