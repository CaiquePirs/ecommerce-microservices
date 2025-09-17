package com.caiquepirs.orders.client.services.representation;

import java.math.BigDecimal;

public record ProductRepresentationDTO(
        Long id,
        String name,
        BigDecimal unitValue) {
}
