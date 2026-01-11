package com.imc.dto;

import java.time.Instant;
import java.util.List;

public record ErrorResponse (
        String errorCode,
        String message,
        Instant timestamp,
        List<FieldViolation> details
) {

    public ErrorResponse(String errorCode, String message, Instant timestamp) {
        this (errorCode, message, timestamp, null);
    }
}
