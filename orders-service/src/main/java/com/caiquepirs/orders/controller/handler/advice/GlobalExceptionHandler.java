package com.caiquepirs.orders.controller.handler.advice;

import com.caiquepirs.orders.controller.handler.dto.ErrorMessage;
import com.caiquepirs.orders.controller.handler.dto.ErrorResponse;
import com.caiquepirs.orders.controller.handler.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse generate(Integer status, String message, List<ErrorMessage> errors){
        return new ErrorResponse(Instant.now(), status, message, errors);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                generate(HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        List.of(new ErrorMessage("Validation", e.getMessage()))
                )
        );
    }

}
