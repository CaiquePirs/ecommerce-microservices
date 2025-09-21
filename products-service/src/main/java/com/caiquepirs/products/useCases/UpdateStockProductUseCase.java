package com.caiquepirs.products.useCases;

import com.caiquepirs.products.controller.advice.exceptions.StockInsufficientException;
import com.caiquepirs.products.model.Product;
import com.caiquepirs.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStockProductUseCase {

    private final ProductRepository productRepository;
    private final FindProductByIdUseCase findProductByIdUseCase;

    public void execute(Long productId, Integer stockUsed){
        Product product = findProductByIdUseCase.execute(productId);

        if(product.getStock() <= 0 || stockUsed > product.getStock()){
            throw new StockInsufficientException(String.format("Product stock with ID: %s is insufficient", productId));
        }

        Integer stockUpdated = product.getStock() - stockUsed;
        product.setStock(stockUpdated);

        productRepository.save(product);
    }
}
