package com.caiquepirs.products.controller;

import com.caiquepirs.products.controller.dto.ProductRequestDTO;
import com.caiquepirs.products.controller.dto.ProductResponseDTO;
import com.caiquepirs.products.mapper.ProductMapper;
import com.caiquepirs.products.model.Product;
import com.caiquepirs.products.useCases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper mapper;
    private final CreateProductUseCase createProductUseCase;
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final DeleteProductByIdUseCase deleteProductByIdUseCase;
    private final UpdateStockProductUseCase updateStockProductUseCase;
    private final AddStockProductUseCase addStockProductUseCase;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO dto){
        Product product = createProductUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable(name = "id") Long productId){
       Product product = findProductByIdUseCase.execute(productId);
       return ResponseEntity.ok(mapper.toResponse(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long productId){
        deleteProductByIdUseCase.execute(productId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStockProduct(@PathVariable(name = "id") Long productId,
                                                   @RequestParam(name = "stock") Integer stockUsed){
        updateStockProductUseCase.execute(productId, stockUsed);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/update-stock")
    public ResponseEntity<Void> addStockProduct(@PathVariable(name = "id") Long productId,
                                                @RequestParam(name = "stock") Integer stockUsed){
        addStockProductUseCase.execute(productId, stockUsed);
        return ResponseEntity.noContent().build();
    }

}
