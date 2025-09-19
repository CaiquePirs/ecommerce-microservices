package com.caiquepirs.invoicing.subscriber.representation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderRepresentation(
        Long id,
        Long customerId,
        String name,
        String cpf,
        String email,
        String phone,
        String neighborhood,
        String number,
        String street,
        LocalDateTime orderDate,
        BigDecimal total,
        List<ItemOrderRepresentation> items) {
}
