package com.caiquepirs.products.useCases;

import com.caiquepirs.products.model.Product;
import com.caiquepirs.products.model.enums.ProductStatus;
import com.caiquepirs.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductByIdUseCase {

    private final ProductRepository productRepository;
    private final FindProductByIdUseCase findProductByIdUseCase;

    public void execute(Long productId){
        Product product = findProductByIdUseCase.execute(productId);
        product.setStatus(ProductStatus.DELETED);
        productRepository.save(product);
    }

}
