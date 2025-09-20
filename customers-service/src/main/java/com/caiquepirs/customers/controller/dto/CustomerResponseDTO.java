package com.caiquepirs.customers.controller.dto;

import com.caiquepirs.customers.model.Address;
import com.caiquepirs.customers.model.enums.UserStatus;

public record CustomerResponseDTO(
        Long id,
        String name,
        String lastName,
        String cpf,
        String email,
        String phone,
        UserStatus status,
        Address address) {
}
