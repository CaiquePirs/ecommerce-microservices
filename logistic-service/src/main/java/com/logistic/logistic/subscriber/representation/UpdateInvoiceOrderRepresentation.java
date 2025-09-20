package com.logistic.logistic.subscriber.representation;

import com.logistic.logistic.model.enuns.StatusOrder;

public record UpdateInvoiceOrderRepresentation(
        Long id,
        StatusOrder status,
        String urlInvoice) {
}
