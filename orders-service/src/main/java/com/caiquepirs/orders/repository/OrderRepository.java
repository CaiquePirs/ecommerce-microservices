package com.caiquepirs.orders.repository;

import com.caiquepirs.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
