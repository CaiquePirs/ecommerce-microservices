package com.caiquepirs.orders.controller.handler.errors;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        Instant timestamp,
        Integer status,
        String message,
        List<ErrorMessage> errors) {
}
