package com.caiquepirs.orders.client.services;

import com.caiquepirs.orders.client.services.api.CustomerClientApi;
import com.caiquepirs.orders.client.services.api.ProductsClientApi;
import com.caiquepirs.orders.client.services.representation.CustomerRepresentationDTO;
import com.caiquepirs.orders.client.services.representation.ProductRepresentationDTO;
import com.caiquepirs.orders.controller.handler.exceptions.ValidationException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientsRepresentationService {

    private final CustomerClientApi customerClientApi;
    private final ProductsClientApi productsClientApi;

    public CustomerRepresentationDTO findCustomer(Long customerId){
        try {
            var customer = customerClientApi.getCustomer(customerId);
            return customer.getBody();

        } catch (FeignException.NotFound e){
            throw new ValidationException("Customer with ID: " + customerId + "not found");
        }
    }

    public ProductRepresentationDTO findProduct(Long productId){
        try {
            var product = productsClientApi.getProduct(productId);
            return product.getBody();

        } catch (FeignException.NotFound e){
            throw new ValidationException("Product with ID: " + productId + "Not found");
        }
    }
}
