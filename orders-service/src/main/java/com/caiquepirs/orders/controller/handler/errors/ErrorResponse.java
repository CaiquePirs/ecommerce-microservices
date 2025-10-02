package com.caiquepirs.orders.controller.handler.errors;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime timestamp,
        Integer status,
        String message,
        List<ErrorMessage> errors) {
}
