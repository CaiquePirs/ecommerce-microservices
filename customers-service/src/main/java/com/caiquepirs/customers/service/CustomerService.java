package com.caiquepirs.customers.service;

import com.caiquepirs.customers.model.Customer;
import com.caiquepirs.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(Long id){
        return customerRepository.findById(id);
    }

}
