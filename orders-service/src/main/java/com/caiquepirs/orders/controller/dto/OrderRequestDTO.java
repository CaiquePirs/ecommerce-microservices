package com.caiquepirs.orders.controller.dto;

import com.caiquepirs.orders.model.PaymentDetails;

import java.util.List;

public record OrderRequestDTO(
        Long customerId,
        List<OrderItemRequestDTO> items,
        PaymentDetails paymentDetails) {
}
