package com.caiquepirs.orders.controller.dto;

import com.caiquepirs.orders.client.services.representation.CustomerRepresentation;
import com.caiquepirs.orders.model.enums.StatusOrder;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record OrderResponseDTO(
        Long id,
        CustomerRepresentation customer,
        LocalDate orderDate,
        BigDecimal total,
        StatusOrder status,
        String invoiceUrl,
        String trackingCode,
        String paymentKey,
        List<ItemOrderResponseDTO> items) {
}
