package com.mvnikitin.boot.rest.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException
        extends RuntimeException implements ProductRESTException {
    private HttpStatus httpStatus;

    public ProductNotFoundException(String message) {
        super(message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    @Override
    public HttpStatus getHTTPStatus() {
        return httpStatus;
    }
}
