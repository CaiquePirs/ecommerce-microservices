package com.caiquepirs.invoicing.controller.advice.erros;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        Instant instant,
        Integer status,
        String message,
        List<ErrorMessage> errors) {
}
