package com.caiquepirs.customers.validator;

import com.caiquepirs.customers.controller.advice.exceptions.CustomerFoundException;
import com.caiquepirs.customers.controller.dto.AddressUpdateDTO;
import com.caiquepirs.customers.model.Address;
import com.caiquepirs.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerValidator {

    private final CustomerRepository customerRepository;

    public void validateIfExistCpf(String cpf){
        customerRepository.findByCpf(cpf).ifPresent(
                customer -> {
                    throw new CustomerFoundException(String.format("This cpf: %s already exist", cpf));
                }
        );
    }

    public void validateIfExistEmail(String email){
        customerRepository.findByEmail(email).ifPresent(
                customer -> {
                    throw new CustomerFoundException(String.format("This email: %s already exist", email));
                }
        );
    }

    public Address validateAddress(Address address, AddressUpdateDTO dto){
        if (dto.city() != null && !dto.city().isBlank()) address.setCity(dto.city());
        if (dto.country() != null && !dto.country().isBlank()) address.setCountry(dto.country());
        if (dto.neighborhood() != null && !dto.neighborhood().isBlank()) address.setNeighborhood(dto.neighborhood());
        if (dto.street() != null && !dto.street().isBlank()) address.setStreet(dto.street());
        if (dto.number() != null) address.setNumber(dto.number());
        if (dto.state() != null && !dto.state().isBlank()) address.setState(dto.state());

        return address;
    }

}
