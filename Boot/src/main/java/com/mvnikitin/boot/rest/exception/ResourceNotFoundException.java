package com.mvnikitin.boot.rest.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException
        extends RuntimeException implements ResourceRESTException {
    private HttpStatus httpStatus;

    public ResourceNotFoundException(String message) {
        super(message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    @Override
    public HttpStatus getHTTPStatus() {
        return httpStatus;
    }
}
