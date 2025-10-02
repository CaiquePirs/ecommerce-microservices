package com.caiquepirs.orders.controller.handler.advice;

import com.caiquepirs.orders.controller.handler.errors.ErrorMessage;
import com.caiquepirs.orders.controller.handler.errors.ErrorResponse;
import com.caiquepirs.orders.controller.handler.exceptions.OrderNotFoundException;
import com.caiquepirs.orders.controller.handler.exceptions.PaymentErrorException;
import com.caiquepirs.orders.controller.handler.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse generate(Integer status, String message, List<ErrorMessage> errors){
        return new ErrorResponse(LocalDateTime.now(), status, message, errors);
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

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(OrderNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                generate(HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        List.of(new ErrorMessage("Order", e.getMessage()))
                )
        );
    }

    @ExceptionHandler(PaymentErrorException.class)
    public ResponseEntity<ErrorResponse> handlePaymentError(PaymentErrorException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                generate(HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        List.of(new ErrorMessage("Payment Error", e.getMessage()))
                )
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        if(e.getLocalizedMessage().contains("PaymentType")) {
            String messageError = "Enter a valid payment type: Debit, Credit, Boleto, or Pix.";

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    generate(HttpStatus.BAD_REQUEST.value(),
                            messageError,
                            List.of(new ErrorMessage("Payment method", messageError))
                    ));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                generate(HttpStatus.BAD_REQUEST.value(),
                        "Error",
                        List.of(new ErrorMessage("Error", e.getMessage()))
                ));
    }

}
