package com.logistic.logistic.event.subscriber.representation;

import com.logistic.logistic.model.enuns.StatusOrder;

public record UpdateInvoiceOrderRepresentation(
        Long id,
        StatusOrder status,
        String urlInvoice) {
}
