package com.caiquepirs.orders.useCases;

import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllOrdersByCustomerIdUseCase {

    private final OrderRepository orderRepository;

    public List<Order> execute(Long customerId){
        return orderRepository.findAllByCustomerId(customerId);
    }

}
