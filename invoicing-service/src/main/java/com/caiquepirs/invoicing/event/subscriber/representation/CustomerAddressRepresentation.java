package com.caiquepirs.invoicing.event.subscriber.representation;

public record CustomerAddressRepresentation(
        String street,
        String number,
        String neighborhood,
        String country,
        String state,
        String city) {
}
