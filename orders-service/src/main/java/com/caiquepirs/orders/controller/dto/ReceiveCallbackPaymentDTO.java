package com.caiquepirs.orders.controller.dto;

public record ReceiveCallbackPaymentDTO(
        Long orderId,
        String paymentKey,
        String notes,
        boolean status) {
}
