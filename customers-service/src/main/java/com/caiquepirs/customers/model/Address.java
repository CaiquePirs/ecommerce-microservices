package com.caiquepirs.customers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Embeddable
public class Address {

    @NotBlank(message = "Street is required")
    @Column(length = 100)
    private String street;

    @NotBlank(message = "Number is required")
    @Column(length = 10)
    private String number;

    @NotBlank(message = "Neighborhood is required")
    @Column(length = 100)
    private String neighborhood;

    @NotBlank(message = "Country is required")
    @Column(length = 100)
    private String country;

    @NotBlank(message = "State is required")
    @Column(length = 100)
    private String state;

    @NotBlank(message = "City is required")
    @Column(length = 100)
    private String city;
}
