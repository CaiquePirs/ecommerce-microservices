package com.caiquepirs.orders.controller.dto;

import java.math.BigDecimal;

public record ItemOrderRequestDTO(
        Long productId,
        Integer quantity,
        BigDecimal unitValue) {
}
