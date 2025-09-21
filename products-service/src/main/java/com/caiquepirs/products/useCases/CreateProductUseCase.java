package com.caiquepirs.products.useCases;

import com.caiquepirs.products.controller.dto.ProductRequestDTO;
import com.caiquepirs.products.mapper.ProductMapper;
import com.caiquepirs.products.model.Product;
import com.caiquepirs.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductMapper mapper;
    private final ProductRepository productRepository;

    public Product execute(ProductRequestDTO dto){
        Product product = mapper.toEntity(dto);
        return productRepository.save(product);
    }

}
