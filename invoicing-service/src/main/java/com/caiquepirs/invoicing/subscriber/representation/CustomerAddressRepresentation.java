package com.caiquepirs.invoicing.subscriber.representation;

public record CustomerAddressRepresentation(
        String street,
        String number,
        String neighborhood,
        String country,
        String state,
        String city) {
}
