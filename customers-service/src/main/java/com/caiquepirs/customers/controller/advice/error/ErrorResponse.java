package com.caiquepirs.customers.controller.advice.error;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErrorResponse(
        LocalDateTime instant,
        Integer status,
        String message,
        List<ErrorMessage> errors) {
}
