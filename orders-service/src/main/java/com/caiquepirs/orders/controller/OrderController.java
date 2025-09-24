package com.caiquepirs.orders.controller;

import com.caiquepirs.orders.controller.dto.OrderRequestDTO;
import com.caiquepirs.orders.controller.dto.UpdateOrderPaymentDTO;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.controller.dto.OrderResponseDTO;
import com.caiquepirs.orders.mapper.GenerateOrderResponse;
import com.caiquepirs.orders.useCases.AddNewPaymentToOrderUseCase;
import com.caiquepirs.orders.useCases.CreateOrderUseCase;
import com.caiquepirs.orders.useCases.FindAllOrdersByCustomerIdUseCase;
import com.caiquepirs.orders.useCases.FindOrderByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final GenerateOrderResponse orderResponse;
    private final CreateOrderUseCase createOrderUseCase;
    private final FindOrderByIdUseCase findOrderByIdUseCase;
    private final AddNewPaymentToOrderUseCase addNewPaymentToOrderUseCase;
    private final FindAllOrdersByCustomerIdUseCase findAllOrdersByCustomerIdUseCase;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderRequestDTO orderRequest){
        Order order = createOrderUseCase.execute(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse.mapToResponse(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findOrderById(@PathVariable(value = "id") Long orderId){
        Order order = findOrderByIdUseCase.execute(orderId);
        return ResponseEntity.ok(orderResponse.mapToResponse(order));
    }

    @PutMapping("/update-payment")
    public ResponseEntity<Void> updateOrderPayment(@RequestBody UpdateOrderPaymentDTO updateOrderPaymentDTO){
        addNewPaymentToOrderUseCase.execute(updateOrderPaymentDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/customer")
    public ResponseEntity<List<OrderResponseDTO>> findAllOrdersByCustomerId(@PathVariable(name = "id") Long customerId){
        List<OrderResponseDTO> response = findAllOrdersByCustomerIdUseCase.execute(customerId)
                .stream()
                .map(orderResponse::mapToResponse)
                .toList();
        return ResponseEntity.ok(response);
    }



}
