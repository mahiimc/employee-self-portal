package com.imc.exception.handler;

import com.imc.dto.ApiError;
import com.imc.dto.ApiResponse;
import com.imc.dto.ErrorResponse;
import com.imc.dto.FieldViolation;
import com.imc.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> handleBaseException(BaseException ex) {
        return  ApiResponse.failure(new ApiError(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception) {
        return  ApiResponse.failure(new ApiError("METHOD_NOT_SUPPORTED", exception.getMessage()));
    }
}
