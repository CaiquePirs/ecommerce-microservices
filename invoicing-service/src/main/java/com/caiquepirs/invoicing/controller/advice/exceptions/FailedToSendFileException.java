package com.caiquepirs.invoicing.controller.advice.exceptions;

public class FailedToSendFileException extends RuntimeException {
    public FailedToSendFileException(String message) {
        super(message);
    }
}
