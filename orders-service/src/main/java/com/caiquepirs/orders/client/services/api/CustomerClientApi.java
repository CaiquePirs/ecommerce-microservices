package com.caiquepirs.orders.client.services.api;

import com.caiquepirs.orders.client.services.representation.CustomerRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "CustomerClient",
        url = "${service-orders.orders.clients.customers.url}")
public interface CustomerClientApi {

    @GetMapping("/{id}")
    ResponseEntity<CustomerRepresentation> getCustomer(@PathVariable Long id);
}
