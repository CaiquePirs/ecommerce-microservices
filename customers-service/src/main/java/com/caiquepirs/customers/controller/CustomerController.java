package com.caiquepirs.customers.controller;

import com.caiquepirs.customers.model.Customer;
import com.caiquepirs.customers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        Customer customerSaved = customerService.create(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable(name = "id") Long customerId){
        return customerService.findById(customerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
