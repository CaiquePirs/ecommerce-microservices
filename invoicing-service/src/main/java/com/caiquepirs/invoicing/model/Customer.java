package com.caiquepirs.invoicing.model;

import lombok.Builder;

@Builder
public record Customer(
        String name,
        String lastName,
        String cpf,
        String email,
        String phone,
        Address address) {
}
