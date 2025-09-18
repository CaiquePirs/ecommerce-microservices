package com.caiquepirs.orders.controller.handler.exceptions;

public class PaymentErrorException extends RuntimeException {
    public PaymentErrorException(String message) {
        super(message);
    }
}
