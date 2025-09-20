package com.caiquepirs.invoicing.subscriber.representation;

import lombok.Builder;

@Builder
public record UpdateStatusOrder(
        Long id,
        StatusOrder status,
        String urlInvoice) {
}
