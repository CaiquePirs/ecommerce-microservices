package com.caiquepirs.orders.repository;

import com.caiquepirs.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndPaymentKey(Long id, String paymentKey);
}
