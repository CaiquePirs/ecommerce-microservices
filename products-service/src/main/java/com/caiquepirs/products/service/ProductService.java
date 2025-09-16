package com.caiquepirs.products.service;

import com.caiquepirs.products.model.Product;
import com.caiquepirs.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> findById(Long productId){
        return productRepository.findById(productId);
    }


}
