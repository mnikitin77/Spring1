package com.mvnikitin.boot.rest;

import java.time.LocalDateTime;

public class ProductErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;

    public int getStatus() {
        return status;
    }
    public void setStatus( int status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
