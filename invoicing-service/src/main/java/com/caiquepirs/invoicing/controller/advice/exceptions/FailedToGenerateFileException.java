package com.caiquepirs.invoicing.controller.advice.exceptions;

public class FailedToGenerateFileException extends RuntimeException {
    public FailedToGenerateFileException(String message) {
        super(message);
    }
}
