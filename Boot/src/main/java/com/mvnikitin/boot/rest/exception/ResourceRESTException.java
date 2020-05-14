package com.mvnikitin.boot.rest.exception;

import org.springframework.http.HttpStatus;

public interface ResourceRESTException {
    HttpStatus getHTTPStatus();
}
