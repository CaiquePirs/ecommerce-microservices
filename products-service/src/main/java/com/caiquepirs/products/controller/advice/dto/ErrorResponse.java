package com.caiquepirs.products.controller.advice.dto;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        Instant instant,
        Integer status,
        String message,
        List<ErrorMessage> errors) {
}
