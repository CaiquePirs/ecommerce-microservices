package com.caiquepirs.orders.client.services;

import com.caiquepirs.orders.client.services.representation.ProductRepresentationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "ProductsClient",
        url = "${service-orders.orders.clients.products.url}")
public interface ProductsClientService {

    @GetMapping("/{id}")
    ResponseEntity<ProductRepresentationDTO> getProduct(Long id);
}
