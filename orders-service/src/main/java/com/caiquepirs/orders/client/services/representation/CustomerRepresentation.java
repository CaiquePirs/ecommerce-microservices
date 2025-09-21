package com.caiquepirs.orders.client.services.representation;

import lombok.Builder;

@Builder
public record CustomerRepresentation(
        Long id,
        String name,
        String lastName,
        String cpf,
        String email,
        String phone,
        CustomerAddressRepresentation address) {
}
