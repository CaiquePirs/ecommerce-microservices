package com.caiquepirs.orders.controller;

import com.caiquepirs.orders.controller.dto.OrderRequestDTO;
import com.caiquepirs.orders.mapper.OrderMapper;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper mapper;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody OrderRequestDTO orderRequest){
        Order order = orderService.order(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable(value = "id") Long orderId){
        Order order = orderService.findOrderById(orderId);
        return ResponseEntity.ok(order);
    }

}
