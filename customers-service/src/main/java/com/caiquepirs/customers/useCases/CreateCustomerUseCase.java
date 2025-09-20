package com.caiquepirs.customers.useCases;

import com.caiquepirs.customers.controller.advice.exceptions.CustomerFoundException;
import com.caiquepirs.customers.controller.dto.CustomerRequestDTO;
import com.caiquepirs.customers.mapper.CustomerMapper;
import com.caiquepirs.customers.model.Customer;
import com.caiquepirs.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Customer execute(CustomerRequestDTO request){
        customerRepository.findByEmail(request.email())
                .ifPresent(customer -> {
                    throw new CustomerFoundException("This email already exist: " + request.email());
                });

       customerRepository.findByCpf(request.cpf())
               .ifPresent(customer -> {
                   throw new CustomerFoundException("This cpf already exit: " + request.cpf());
       });

        Customer customer = customerMapper.toEntity(request);
        return customerRepository.save(customer);
    }

}
