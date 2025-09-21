package com.caiquepirs.orders.client.services.representation;

import lombok.Builder;

@Builder
public record CustomerAddressRepresentation(
        String street,
        String number,
        String neighborhood,
        String country,
        String state,
        String city) {
}
