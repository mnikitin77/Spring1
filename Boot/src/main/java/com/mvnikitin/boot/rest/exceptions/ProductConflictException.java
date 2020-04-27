package com.mvnikitin.boot.rest.exceptions;

import org.springframework.http.HttpStatus;

public class ProductConflictException
        extends RuntimeException implements ProductRESTException {
    private HttpStatus httpStatus;

    public ProductConflictException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }

    @Override
    public HttpStatus getHTTPStatus() {
        return httpStatus;
    }
}
