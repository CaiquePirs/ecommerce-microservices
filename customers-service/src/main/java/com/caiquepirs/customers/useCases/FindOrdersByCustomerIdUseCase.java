package com.caiquepirs.customers.useCases;

import com.caiquepirs.customers.client.api.OrdersClientApi;
import com.caiquepirs.customers.client.representation.OrdersRepresentation;
import com.caiquepirs.customers.controller.advice.exceptions.CustomerNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindOrdersByCustomerIdUseCase {

    private final OrdersClientApi orderClientApi;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;

    public List<OrdersRepresentation> execute(Long customerId) {
        try {
            findCustomerByIdUseCase.execute(customerId);

            var response = orderClientApi.findAllOrdersByCustomerId(customerId);
            return response.getBody();

        } catch (FeignException e) {
            throw new CustomerNotFoundException(String.format("Orders by customer ID: %s not found", customerId));
        }

    }
}
