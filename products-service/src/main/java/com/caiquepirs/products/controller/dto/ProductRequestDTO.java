package com.caiquepirs.products.controller.dto;

import com.caiquepirs.products.model.enums.ProductCategory;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotBlank(message = "Name is required")
        @Length(max = 100, message = "Name can only have up to 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotBlank(message = "Brand is required")
        String brand,

        @NotNull(message = "Unit value is required")
        @DecimalMin(value = "0.01", inclusive = true, message = "Unit value must be greater than 0")
        @Digits(integer = 14, fraction = 2, message = "Unit value must have up to 14 digits and 2 decimals")
        BigDecimal unitValue,

        @NotNull(message = "Stock is required")
        @Min(value = 0, message = "Stock must be greater than or equal to 0")
        Integer stock,

        @NotNull(message = "Category is required")
        ProductCategory category
) {
}
