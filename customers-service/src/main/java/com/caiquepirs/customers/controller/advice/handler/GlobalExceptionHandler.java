package com.caiquepirs.customers.controller.advice.handler;

import com.caiquepirs.customers.controller.advice.error.ErrorMessage;
import com.caiquepirs.customers.controller.advice.error.ErrorResponse;
import com.caiquepirs.customers.controller.advice.exceptions.CustomerFoundException;
import com.caiquepirs.customers.controller.advice.exceptions.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse generate(Integer status, String message, List<ErrorMessage> errors){
        return ErrorResponse.builder()
                .instant(LocalDateTime.now())
                .status(status)
                .message(message)
                .errors(errors)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorMessage> listErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new ErrorMessage(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error",
                listErrors
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(CustomerFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerFound(CustomerFoundException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                generate(HttpStatus.CONFLICT.value(),
                        e.getMessage(),
                        List.of(new ErrorMessage("Customer Found", e.getMessage()))
                ));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(CustomerNotFoundException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                generate(HttpStatus.CONFLICT.value(),
                        e.getMessage(),
                        List.of(new ErrorMessage("Customer not Found", e.getMessage()))
                ));
    }

}
