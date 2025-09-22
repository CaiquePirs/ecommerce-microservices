package com.caiquepirs.customers.controller.dto;

import com.caiquepirs.customers.model.Address;

public record CustomerResponseDTO(
        Long id,
        String name,
        String lastName,
        String cpf,
        String email,
        String phone,
        Address address) {
}
