package com.caiquepirs.customers.controller;

import com.caiquepirs.customers.client.representation.OrdersRepresentation;
import com.caiquepirs.customers.controller.dto.CustomerRequestDTO;
import com.caiquepirs.customers.controller.dto.CustomerResponseDTO;
import com.caiquepirs.customers.controller.dto.CustomerUpdateDTO;
import com.caiquepirs.customers.mapper.CustomerMapper;
import com.caiquepirs.customers.model.Customer;
import com.caiquepirs.customers.useCases.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final DeactivateCustomerByIdUseCase deactivateCustomerByIdUseCase;
    private final UpdateCustomerByIdUseCase updateCustomerByIdUseCase;
    private final FindOrdersByCustomerIdUseCase findOrdersByCustomerIdUseCase;
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

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrdersRepresentation>> findAllOrdersByCustomerId(@PathVariable(name = "id") Long customerId){
        return ResponseEntity.ok(findOrdersByCustomerIdUseCase.execute(customerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateById(@PathVariable(name = "id") Long customerId){
        deactivateCustomerByIdUseCase.execute(customerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateById(@PathVariable(name = "id") Long customerId,
                                                          @RequestBody @Valid CustomerUpdateDTO dto) {
        Customer customer = updateCustomerByIdUseCase.execute(customerId, dto);
        return ResponseEntity.ok(mapper.toDTO(customer));
    }

}
