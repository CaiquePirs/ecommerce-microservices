package com.caiquepirs.orders.controller.dto;

import com.caiquepirs.orders.model.enums.PaymentType;

public record UpdateOrderPaymentDTO(
        Long orderId,
        String paymentData,
        PaymentType paymentType) {
}
