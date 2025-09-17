package com.caiquepirs.orders.validator;

import com.caiquepirs.orders.client.services.CustomerClientService;
import com.caiquepirs.orders.client.services.ProductsClientService;
import com.caiquepirs.orders.controller.handler.exceptions.ValidationException;
import com.caiquepirs.orders.model.Order;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderValidator {

    private final ProductsClientService productsClientService;
    private final CustomerClientService customerClientService;

    public void validate(Order order){
        customerValidate(order.getCustomerId());
        order.getItems().forEach(i -> productValidate(i.getProductId()));
    }

    private void customerValidate(Long customerId){
        try {
            customerClientService.getCustomer(customerId);
            log.info("Customer with id {} found successfully", customerId);

        } catch (FeignException.NotFound e) {
            throw new ValidationException("Customer with ID: " + customerId + " Not found");
        }
    }

    private void productValidate(Long productId){
        try {
            productsClientService.getProduct(productId);
            log.info("Product with id {} found successfully", productId);

        } catch (FeignException.NotFound e){
            throw new ValidationException("Product with ID: " + productId + "Not found");
        }
    }

}
