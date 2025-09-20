package com.caiquepirs.customers.controller.advice.error;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ErrorResponse(
        Instant instant,
        Integer status,
        String message,
        List<ErrorMessage> errors) {
}
