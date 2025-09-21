package com.caiquepirs.orders.model;

import com.caiquepirs.orders.model.enums.StatusOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(columnDefinition = "text")
    private String notes;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private StatusOrder status;

    @Column(nullable = false, precision = 16, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    @Transient
    private PaymentDetails paymentDetails;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate = LocalDate.now();

    @Column(name = "payment_key")
    private String paymentKey;

    @Column(name = "tracking_code", length = 255)
    private String trackingCode;

    @Column(name = "invoice_url", columnDefinition = "text")
    private String invoiceUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
