package com.caiquepirs.invoicing.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record Order(
        Long id,
        Customer customer,
        LocalDateTime orderDate,
        List<ItemOrder> itemOrders,
        BigDecimal total) {
}
