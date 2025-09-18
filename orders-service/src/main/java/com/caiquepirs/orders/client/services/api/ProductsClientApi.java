package com.caiquepirs.orders.client.services.api;

import com.caiquepirs.orders.client.services.representation.ProductRepresentationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ProductsClient",
        url = "${service-orders.orders.clients.products.url}")
public interface ProductsClientApi {

    @GetMapping("/{id}")
    ResponseEntity<ProductRepresentationDTO> getProduct(@PathVariable Long id);
}
