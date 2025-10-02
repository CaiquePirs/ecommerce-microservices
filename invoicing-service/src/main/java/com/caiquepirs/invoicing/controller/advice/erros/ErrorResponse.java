package com.caiquepirs.invoicing.controller.advice.erros;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime instant,
        Integer status,
        String message,
        List<ErrorMessage> errors) {
}
