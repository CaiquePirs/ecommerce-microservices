package com.caiquepirs.invoicing.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemOrder {

    private Long id;
    private String productName;
    private BigDecimal unitValue;
    private Integer quantity;
    private BigDecimal total;

}
