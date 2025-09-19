package com.caiquepirs.invoicing.controller.advice.handler;

import com.caiquepirs.invoicing.controller.advice.erros.ErrorMessage;
import com.caiquepirs.invoicing.controller.advice.erros.ErrorResponse;
import com.caiquepirs.invoicing.controller.advice.exceptions.InputFileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse generator(Integer status, String message, List<ErrorMessage> errorMessages){
        return new ErrorResponse(
                Instant.now(),
                status,
                message,
                errorMessages);
    }

    @ExceptionHandler(InputFileException.class)
    public ResponseEntity<ErrorResponse> handleInputFile(InputFileException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                generator(HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        List.of(new ErrorMessage("Error File", e.getMessage())))
        );
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                generator(HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        List.of(new ErrorMessage("Error File", e.getMessage())))
        );
    }
}
