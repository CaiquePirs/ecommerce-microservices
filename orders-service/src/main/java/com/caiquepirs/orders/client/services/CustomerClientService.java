package com.caiquepirs.orders.client.services;


import com.caiquepirs.orders.client.services.representation.CustomerRepresentationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "CustomerClient",
        url = "${service-orders.orders.clients.customers.url}")
public interface CustomerClientService {

    @GetMapping("/{id}")
    ResponseEntity<CustomerRepresentationDTO> getCustomer(Long id);
}
