package com.caiquepirs.customers.client.representation;

import java.math.BigDecimal;

public record ItemOrdersRepresentation(
        Long id,
        String productName,
        Integer quantity,
        BigDecimal unitValue,
        BigDecimal total) {
}
