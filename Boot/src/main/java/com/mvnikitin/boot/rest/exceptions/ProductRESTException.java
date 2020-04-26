package com.mvnikitin.boot.rest.exceptions;

import org.springframework.http.HttpStatus;

public interface ProductRESTException {
    HttpStatus getHTTPStatus();
}
