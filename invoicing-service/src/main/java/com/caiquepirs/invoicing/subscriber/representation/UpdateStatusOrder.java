package com.caiquepirs.invoicing.subscriber.representation;

import com.caiquepirs.invoicing.subscriber.representation.enuns.StatusOrder;
import lombok.Builder;

@Builder
public record UpdateStatusOrder(
        Long id,
        StatusOrder status,
        String urlInvoice) {
}
