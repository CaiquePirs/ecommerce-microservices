package com.caiquepirs.invoicing.model;

import lombok.Builder;

@Builder
public record Customer(
        String name,
        String cpf,
        String email,
        String phone,
        String neighborhood,
        String street,
        String number) {
}
