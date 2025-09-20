package com.caiquepirs.customers.useCases;

import com.caiquepirs.customers.controller.advice.exceptions.CustomerNotFoundException;
import com.caiquepirs.customers.model.Customer;
import com.caiquepirs.customers.model.enums.UserStatus;
import com.caiquepirs.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindCustomerByIdUseCase {

    private final CustomerRepository customerRepository;

    public Customer execute(Long customerId){
        return customerRepository.findById(customerId)
                .filter(c -> !c.getStatus().equals(UserStatus.DELETED))
                .orElseThrow(() -> new CustomerNotFoundException("Customer ID: " + customerId + " not found"));
    }

}
