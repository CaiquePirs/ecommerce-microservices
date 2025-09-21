package com.caiquepirs.orders.controller.dto;

public record ItemOrderRequestDTO(
        Long productId,
        Integer quantity) {
}
