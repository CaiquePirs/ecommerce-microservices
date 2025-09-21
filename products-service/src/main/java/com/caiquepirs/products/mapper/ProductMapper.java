package com.caiquepirs.products.mapper;

import com.caiquepirs.products.controller.dto.ProductRequestDTO;
import com.caiquepirs.products.controller.dto.ProductResponseDTO;
import com.caiquepirs.products.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequestDTO dto);
    ProductResponseDTO toResponse(Product product);
}
