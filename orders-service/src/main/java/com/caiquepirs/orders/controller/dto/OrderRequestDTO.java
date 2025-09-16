package com.caiquepirs.orders.controller.dto;

import java.util.List;

public record OrderRequestDTO(
        Long customerId,
        List<OrderItemRequestDTO> items,
        PaymentDetailsDTO detailsDTO) {
}
