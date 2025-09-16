package com.caiquepirs.orders.controller.dto;

import com.caiquepirs.orders.model.enums.PaymentType;

public record PaymentDetailsDTO(
        String data,
        PaymentType paymentType) {
}