package com.sk.learn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class IgnoreException extends HttpClientErrorException {

    public IgnoreException (HttpStatus message) {
        super(message);
    }

    public IgnoreException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }
}
