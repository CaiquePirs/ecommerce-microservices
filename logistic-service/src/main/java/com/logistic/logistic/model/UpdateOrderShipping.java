package com.logistic.logistic.model;

import com.logistic.logistic.model.enuns.StatusOrder;
import lombok.Builder;

@Builder
public record UpdateOrderShipping(
        Long id,
        StatusOrder status,
        String trackingCode) {
}
