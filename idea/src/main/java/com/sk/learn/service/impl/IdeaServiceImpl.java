package com.sk.learn.service.impl;

import com.sk.learn.exception.IgnoreException;
import com.sk.learn.service.IdeaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class IdeaServiceImpl implements IdeaService {

    @Override
    public String apiSuccessResponse() {
        return "What an Idea! Sir";
    }

    @Override
    public String apiSuccessWithException() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Throwing exception to make sure api returns success response. We know its ok to throw error in specific scenarios");
    }

    @Override
    public String apiFailureResponse() {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Throwing exception to make sure we are able to see the failure");
    }

    @Override
    public String apiFailureWithFallback() {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Throwing exception to make sure it goes in the fallback when fallback method is configured");
    }

    @Override
    public String apiIgnoreException() {
        throw new IgnoreException(HttpStatus.BAD_REQUEST, "Please ignore this exception as its configured in the CircuitBreaker : Idea");
    }
}
