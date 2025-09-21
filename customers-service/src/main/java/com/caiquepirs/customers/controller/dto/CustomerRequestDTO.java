package com.caiquepirs.customers.controller.dto;

import com.caiquepirs.customers.model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CustomerRequestDTO(

        @Length(max = 150, message = "Name can only have up to 150 characters")
        @NotBlank(message = "Name is required")
        String name,

        @Length(max = 100, message = "Last name can only have up to 100 characters")
        @NotBlank(message = "Last name is required")
        String lastName,

        @Length(max = 11, message = "CPF can only have up to 11 characters")
        @NotBlank(message = "CPF is required")
        String cpf,

        @Length(max = 150, message = "Email can only have up to 150 characters")
        @Email(message = "Email must be valid")
        @NotBlank(message = "Email is required")
        String email,

        @Length(min = 8, message = "Password must be at least 8 characters long")
        @NotBlank(message = "Password is required")
        String password,

        @Length(max = 20, message = "Phone can only have up to 20 characters")
        @NotBlank(message = "Phone is required")
        String phone,

        @Valid
        @NotNull(message = "Address is required")
        Address address) {
}

