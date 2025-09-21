package com.caiquepirs.orders.client.services.api;

import com.caiquepirs.orders.client.services.representation.ProductRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "ProductsClient",
        url = "${service-orders.orders.clients.products.url}")
public interface ProductClientApi {

    @GetMapping("/{id}")
    ResponseEntity<ProductRepresentation> getProduct(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> updateStock(@PathVariable(name = "id") Long productId,
                                     @RequestParam(name = "stock") Integer stockUsed);
}
