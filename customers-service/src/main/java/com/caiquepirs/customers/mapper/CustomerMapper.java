package com.caiquepirs.customers.mapper;

import com.caiquepirs.customers.controller.dto.CustomerRequestDTO;
import com.caiquepirs.customers.controller.dto.CustomerResponseDTO;
import com.caiquepirs.customers.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO toDTO(Customer customer);
}
