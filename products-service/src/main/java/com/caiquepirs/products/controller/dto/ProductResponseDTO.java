package com.caiquepirs.products.controller.dto;

import com.caiquepirs.products.model.enums.ProductCategory;
import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        String brand,
        BigDecimal unitValue,
        Integer stock,
        ProductCategory category) {
}