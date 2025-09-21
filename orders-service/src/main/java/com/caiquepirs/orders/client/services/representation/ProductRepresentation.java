package com.caiquepirs.orders.client.services.representation;

import java.math.BigDecimal;

public record ProductRepresentation(
        Long id,
        String name,
        String description,
        String brand,
        BigDecimal unitValue,
        Integer stock,
        String category) {
}
