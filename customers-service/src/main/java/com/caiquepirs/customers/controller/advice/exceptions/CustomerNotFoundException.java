package com.caiquepirs.customers.controller.advice.exceptions;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(String message) {
    super(message);
  }
}
