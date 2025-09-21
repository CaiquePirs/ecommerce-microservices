package com.caiquepirs.customers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Embeddable
public class Address {

    @NotBlank(message = "Street is required")
    @Length(max = 100, message = "Street can only have up to 100 characters")
    @Column(length = 100)
    private String street;

    @NotBlank(message = "Number is required")
    @Length(max = 10, message = "Number can only have up to 10 characters")
    @Column(length = 10)
    private String number;

    @NotBlank(message = "Neighborhood is required")
    @Length(max = 100, message = "Neighborhood can only have up to 100 characters")
    @Column(length = 100)
    private String neighborhood;

    @NotBlank(message = "Country is required")
    @Length(max = 100, message = "Country can only have up to 100 characters")
    @Column(length = 100)
    private String country;

    @NotBlank(message = "State is required")
    @Length(max = 100, message = "State can only have up to 100 characters")
    @Column(length = 100)
    private String state;

    @NotBlank(message = "City is required")
    @Length(max = 100, message = "City can only have up to 100 characters")
    @Column(length = 100)
    private String city;
}
