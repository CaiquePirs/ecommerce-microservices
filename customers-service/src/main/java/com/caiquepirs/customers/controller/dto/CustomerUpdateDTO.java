package com.caiquepirs.customers.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public record CustomerUpdateDTO(

        @Length(max = 150, message = "Name can only have up to 150")
        String name,

        @Length(max = 100, message = "Last name can only have up to 100")
        String lastName,

        @Length(max = 11, message = "CPF can only have up to 11")
        String cpf,

        @Length(max = 150, message = "Email can only have up t 150")
        @Email(message = "Email must be valid")
        String email,

        @Length(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @Length(max = 20, message = "Phone can only have up to 20")
        String phone,

        @Valid
        AddressUpdateDTO addressUpdateDTO) {
}
