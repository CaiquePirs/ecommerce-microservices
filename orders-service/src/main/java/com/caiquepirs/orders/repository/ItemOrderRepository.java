package com.caiquepirs.orders.repository;

import com.caiquepirs.orders.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepository extends JpaRepository<OrderItem, Long> {
}
