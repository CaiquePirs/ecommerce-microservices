package com.caiquepirs.invoicing.subscriber.representation;

public record CustomerRepresentation(
        Long id,
        String name,
        String lastName,
        String cpf,
        String email,
        String phone,
        CustomerAddressRepresentation address) {
}
