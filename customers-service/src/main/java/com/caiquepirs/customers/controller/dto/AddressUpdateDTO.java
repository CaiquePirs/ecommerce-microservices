package com.caiquepirs.customers.controller.dto;

import org.hibernate.validator.constraints.Length;

public record AddressUpdateDTO(
        @Length(max = 100, message = "Street can only have up to 100 characters")
        String street,

        @Length(max = 10, message = "Number can only have up to 10 characters")
        String number,

        @Length(max = 100, message = "Neighborhood can only have up to 100 characters")
        String neighborhood,

        @Length(max = 100, message = "Country can only have up to 100 characters")
        String country,

        @Length(max = 100, message = "State can only have up to 100 characters")
        String state,

        @Length(max = 100, message = "City can only have up to 100 characters")
        String city
) {
}
