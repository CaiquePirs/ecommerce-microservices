package com.caiquepirs.products.useCases;

import com.caiquepirs.products.model.Product;
import com.caiquepirs.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddStockProductUseCase {

    private final ProductRepository productRepository;
    private final FindProductByIdUseCase findProductByIdUseCase;

    public void execute(Long productId, Integer newStock){
        Product product = findProductByIdUseCase.execute(productId);

        Integer updatedStock = product.getStock() + newStock;

        product.setStock(updatedStock);
        productRepository.save(product);
    }
}
