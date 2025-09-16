package com.caiquepirs.orders.controller.dto;

import java.math.BigDecimal;

public record OrderItemRequestDTO(
        Long productId,
        Integer quantity,
        BigDecimal unitValue) {
}
