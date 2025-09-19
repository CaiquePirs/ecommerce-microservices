package com.caiquepirs.invoicing.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemOrder {

    private Long id;
    private String name;
    private BigDecimal unitValue;
    private Integer quantity;

    public BigDecimal getTotal(){
        return this.unitValue.multiply(BigDecimal.valueOf(quantity));
    }

}
