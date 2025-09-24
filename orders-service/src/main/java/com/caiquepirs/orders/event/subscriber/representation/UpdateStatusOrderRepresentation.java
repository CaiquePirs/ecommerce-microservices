package com.caiquepirs.orders.event.subscriber.representation;

import com.caiquepirs.orders.model.enums.StatusOrder;

public record UpdateStatusOrderRepresentation(
        Long id,
        StatusOrder status,
        String urlInvoice,
        String trackingCode) {
}
