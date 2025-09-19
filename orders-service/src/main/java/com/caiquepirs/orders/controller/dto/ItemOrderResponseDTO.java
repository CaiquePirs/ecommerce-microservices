package com.caiquepirs.orders.controller.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemOrderResponseDTO(Long id,
                                   String productName,
                                   Integer quantity,
                                   BigDecimal unitValue,
                                   BigDecimal total) {
}
