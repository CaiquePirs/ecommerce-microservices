package com.caiquepirs.invoicing.event.subscriber.representation;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemOrderRepresentation(
        Long id,
        String productName,
        Integer quantity,
        BigDecimal unitValue,
        BigDecimal total) {
}
