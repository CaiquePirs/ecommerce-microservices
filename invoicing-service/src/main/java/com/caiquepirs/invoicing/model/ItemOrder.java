package com.caiquepirs.invoicing.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemOrder(
        Long id,
        String description,
        BigDecimal unitValue,
        Integer quantity) {

    public BigDecimal getTotal(){
        return this.unitValue.multiply(BigDecimal.valueOf(quantity));
    }

}
