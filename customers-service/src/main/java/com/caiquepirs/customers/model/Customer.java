package com.caiquepirs.customers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers-db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(length = 100)
    private String street;

    @Column(length = 10)
    private String number;

    @Column(length = 100)
    private String neighborhood;

    @Column(length = 150)
    private String email;

    @Column(length = 20)
    private String phone;
}
