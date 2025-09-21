package com.caiquepirs.customers.useCases;

import com.caiquepirs.customers.controller.dto.CustomerUpdateDTO;
import com.caiquepirs.customers.validator.CustomerValidator;
import com.caiquepirs.customers.model.Address;
import com.caiquepirs.customers.model.Customer;
import com.caiquepirs.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerByIdUseCase {

    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;

    public Customer execute(Long customerId, CustomerUpdateDTO updateDTO){
        Customer customer = findCustomerByIdUseCase.execute(customerId);

        if(updateDTO.email() != null && !updateDTO.email().isBlank()){
            customerValidator.validateIfExistEmail(updateDTO.email());
            customer.setEmail(updateDTO.email());
        }

        if(updateDTO.cpf() != null && !updateDTO.cpf().isBlank()){
            customerValidator.validateIfExistCpf(updateDTO.cpf());
            customer.setCpf(updateDTO.cpf());
        }

        Address addressUpdated = customerValidator.validateAddress(
                customer.getAddress(),
                updateDTO.addressUpdateDTO()
        );

        customer.setAddress(addressUpdated);
        return customerRepository.save(customer);
    }
}
