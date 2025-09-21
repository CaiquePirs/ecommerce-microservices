package com.caiquepirs.products.useCases;

import com.caiquepirs.products.controller.advice.exceptions.ProductNotFoundException;
import com.caiquepirs.products.model.Product;
import com.caiquepirs.products.model.enums.ProductStatus;
import com.caiquepirs.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindProductByIdUseCase {

    private final ProductRepository productRepository;

    public Product execute(Long productId){
        return productRepository.findById(productId)
                .filter(product -> !product.getStatus().equals(ProductStatus.DELETED))
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product ID: %s not found", productId)));
    }

}
