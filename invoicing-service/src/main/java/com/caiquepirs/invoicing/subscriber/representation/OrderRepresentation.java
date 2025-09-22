package com.caiquepirs.invoicing.subscriber.representation;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderRepresentation(
        Long id,
        CustomerRepresentation customer,
        LocalDate orderDate,
        BigDecimal total,
        String invoiceUrl,
        List<ItemOrderRepresentation> items) {
}
