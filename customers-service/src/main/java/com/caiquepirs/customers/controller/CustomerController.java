package com.caiquepirs.customers.controller;

import com.caiquepirs.customers.controller.dto.CustomerRequestDTO;
import com.caiquepirs.customers.controller.dto.CustomerResponseDTO;
import com.caiquepirs.customers.mapper.CustomerMapper;
import com.caiquepirs.customers.model.Customer;
import com.caiquepirs.customers.useCases.CreateCustomerUseCase;
import com.caiquepirs.customers.useCases.FindCustomerByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final CustomerMapper mapper;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@RequestBody @Valid CustomerRequestDTO request){
        Customer customer = createCustomerUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable(name = "id") Long customerId){
        Customer customer = findCustomerByIdUseCase.execute(customerId);
        return ResponseEntity.ok(mapper.toDTO(customer));
    }

}
