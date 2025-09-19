package com.caiquepirs.orders.controller;

import com.caiquepirs.orders.controller.dto.OrderRequestDTO;
import com.caiquepirs.orders.controller.dto.UpdateOrderPaymentDTO;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.controller.dto.OrderResponseDTO;
import com.caiquepirs.orders.generator.GenerateOrderResponse;
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
    private final GenerateOrderResponse orderResponse;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody OrderRequestDTO orderRequest){
        orderService.order(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findOrderById(@PathVariable(value = "id") Long orderId){
        Order order = orderService.findOrderById(orderId);;
        return ResponseEntity.ok(orderResponse.mapToResponse(order));
    }

    @PutMapping("/update-payment")
    public ResponseEntity<Void> updateOrderPayment(@RequestBody UpdateOrderPaymentDTO updateOrderPaymentDTO){
        orderService.addNewPayment(updateOrderPaymentDTO);
        return ResponseEntity.noContent().build();
    }
}
