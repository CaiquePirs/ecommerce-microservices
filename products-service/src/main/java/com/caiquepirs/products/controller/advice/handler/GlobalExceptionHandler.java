package com.caiquepirs.products.controller.advice.handler;

import com.caiquepirs.products.controller.advice.dto.ErrorMessage;
import com.caiquepirs.products.controller.advice.dto.ErrorResponse;
import com.caiquepirs.products.controller.advice.exceptions.ProductNotFoundException;
import com.caiquepirs.products.controller.advice.exceptions.StockInsufficientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorMessage> listErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new ErrorMessage(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(
                Instant.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error",
                listErrors
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(StockInsufficientException.class)
    public ResponseEntity<ErrorResponse> handleStockInsufficient(StockInsufficientException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                List.of(new ErrorMessage("Stock Insufficient", e.getMessage()))
        ));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                List.of(new ErrorMessage("Not found", e.getMessage()))
        ));
    }

}
