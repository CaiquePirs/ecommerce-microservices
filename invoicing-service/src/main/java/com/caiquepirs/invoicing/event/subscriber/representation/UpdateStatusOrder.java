package com.caiquepirs.invoicing.event.subscriber.representation;

import com.caiquepirs.invoicing.event.subscriber.representation.enuns.StatusOrder;
import lombok.Builder;

@Builder
public record UpdateStatusOrder(
        Long id,
        StatusOrder status,
        String urlInvoice) {
}
