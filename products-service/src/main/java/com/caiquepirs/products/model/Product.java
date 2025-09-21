package com.caiquepirs.products.model;

import com.caiquepirs.products.model.enums.ProductCategory;
import com.caiquepirs.products.model.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String brand;

    @Column(name = "unit_value", nullable = false, precision = 16, scale = 2)
    private BigDecimal unitValue;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.ACTIVE;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

}
