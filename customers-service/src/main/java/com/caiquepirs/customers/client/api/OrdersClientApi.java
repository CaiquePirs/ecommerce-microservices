package com.caiquepirs.customers.client.api;

import com.caiquepirs.customers.client.representation.OrdersRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "orders",
        url = "${service-customer.customer.clients.orders.url}"
)
public interface OrdersClientApi {

    @GetMapping("/{id}/customer")
    ResponseEntity<List<OrdersRepresentation>> findAllOrdersByCustomerId(@PathVariable(name = "id") Long customerId);
}
