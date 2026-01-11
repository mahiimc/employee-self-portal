package com.imc.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ApiMeta {

    private LocalDateTime timestamp;
    private String requestId;

    public static ApiMeta now() {
        return new ApiMeta(LocalDateTime.now(), UUID.randomUUID().toString());
    }

    private ApiMeta(LocalDateTime timestamp, String requestId) {
        this.timestamp = timestamp;
        this.requestId = requestId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
