package com.mvnikitin.boot.rest.exception;

import org.springframework.http.HttpStatus;

public class ResourceConflictException
        extends RuntimeException implements ResourceRESTException {
    private HttpStatus httpStatus;

    public ResourceConflictException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }

    @Override
    public HttpStatus getHTTPStatus() {
        return httpStatus;
    }
}
