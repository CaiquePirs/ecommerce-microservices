package com.caiquepirs.customers.useCases;

import com.caiquepirs.customers.model.Customer;
import com.caiquepirs.customers.model.enums.UserStatus;
import com.caiquepirs.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeactivateCustomerByIdUseCase {

    private final CustomerRepository customerRepository;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;

    public void execute(Long customerId){
        Customer customer = findCustomerByIdUseCase.execute(customerId);
        customer.setStatus(UserStatus.DELETED);
        customerRepository.save(customer);
    }

}
