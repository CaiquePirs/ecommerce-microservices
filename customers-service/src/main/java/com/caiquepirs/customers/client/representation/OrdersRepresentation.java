package com.caiquepirs.customers.client.representation;

import com.caiquepirs.customers.client.representation.enums.StatusOrder;
import com.caiquepirs.customers.controller.dto.CustomerResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrdersRepresentation(
        Long id,
        CustomerResponseDTO customer,
        LocalDate orderDate,
        BigDecimal total,
        StatusOrder status,
        String invoiceUrl,
        String trackingCode,
        List<ItemOrdersRepresentation> items) {
}
