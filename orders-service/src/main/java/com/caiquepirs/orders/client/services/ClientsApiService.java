package com.caiquepirs.orders.client.services;

import com.caiquepirs.orders.client.services.api.CustomerClientApi;
import com.caiquepirs.orders.client.services.api.ProductClientApi;
import com.caiquepirs.orders.client.services.representation.CustomerRepresentation;
import com.caiquepirs.orders.client.services.representation.ProductRepresentation;
import com.caiquepirs.orders.controller.handler.exceptions.ValidationException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientsApiService {

    private final CustomerClientApi customerClientApi;
    private final ProductClientApi productClientApi;

    public CustomerRepresentation findCustomer(Long customerId){
        try {
            var customer = customerClientApi.getCustomer(customerId);
            return customer.getBody();

        } catch (FeignException e){
            throw new ValidationException(String.format("Customer ID: %s not found", customerId));
        }
    }

    public ProductRepresentation findProduct(Long productId) {
        try {
            var response = productClientApi.getProduct(productId);
            return response.getBody();

        } catch (FeignException e) {
            throw new ValidationException(String.format("Product ID: %s not found", productId));
        }
    }

    public void updateProductStock(Long productId, Integer stockUsed){
        try {
            productClientApi.updateStock(productId, stockUsed);

        } catch (FeignException e){
            throw new ValidationException(String.format("Error updating product stock with ID: %s ", productId));
        }
    }

}
