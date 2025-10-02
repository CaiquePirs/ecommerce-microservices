package com.caiquepirs.products.controller.advice.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime instant,
        Integer status,
        String message,
        List<ErrorMessage> errors) {
}
