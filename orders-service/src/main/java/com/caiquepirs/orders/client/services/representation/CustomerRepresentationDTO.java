package com.caiquepirs.orders.client.services.representation;

public record CustomerRepresentationDTO(
        Long id,
        String name,
        String cpf,
        String street,
        String number,
        String neighborhood,
        String email,
        String phone) {
}
