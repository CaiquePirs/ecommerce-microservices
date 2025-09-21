package com.caiquepirs.products.controller.advice.exceptions;

public class StockInsufficientException extends RuntimeException {
    public StockInsufficientException(String message) {
        super(message);
    }
}
