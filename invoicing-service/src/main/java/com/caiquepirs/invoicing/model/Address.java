package com.caiquepirs.invoicing.model;

import lombok.Builder;

@Builder
public record Address(
        String street,
        String number,
        String neighborhood,
        String country,
        String state,
        String city
) {
}
