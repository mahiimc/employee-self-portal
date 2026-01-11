package com.imc.exception.handler;

import com.imc.dto.ApiError;
import com.imc.dto.ApiResponse;
import com.imc.dto.ErrorResponse;
import com.imc.dto.FieldViolation;
import com.imc.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ApiResponse<ErrorResponse> handleBaseException(BaseException ex) {
        return  ApiResponse.failure(new ApiError(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        var violations  = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(fe -> new FieldViolation(fe.getField(), fe.getDefaultMessage()))
                .toList();

        String aggregatedMessage = violations.stream()
                .map(v -> v.field() + " : " + v.message())
                .reduce((a,b) -> a + "; " + b)
                .orElse("Validation failed");

        ErrorResponse response = new ErrorResponse(
                "VALIDATION_ERROR",
                aggregatedMessage,
                Instant.now(),
                violations
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
