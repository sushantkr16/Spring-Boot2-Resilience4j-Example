package com.sk.learn.service.impl;

import com.sk.learn.client.TeamFacade;
import com.sk.learn.service.TeamService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    private static final String IDEA = "idea";

    @Autowired
    private TeamFacade teamFacade;

    @Override
    @CircuitBreaker(name = IDEA)
    @Bulkhead(name = IDEA)
    @Retry(name = IDEA)
    public String apiFailureResponse() {
        return teamFacade.apiFailureResponse();
    }

    @Override
    @CircuitBreaker(name = IDEA, fallbackMethod = "apiFallback")
    public String apiFailureWithFallback() {
        return teamFacade.apiFailureWithFallback();
    }

    @Override
    @CircuitBreaker(name = IDEA)
    @Bulkhead(name = IDEA)
    @Retry(name = IDEA)
    public String apiSuccessResponse() {
        return teamFacade.apiSuccessResponse();
    }

    @Override
    @CircuitBreaker(name = IDEA)
    public String apiSuccessWithException() {
        return teamFacade.apiSuccessWithException();
    }

    @Override
    @CircuitBreaker(name = IDEA)
    @Bulkhead(name = IDEA)
    @Retry(name = IDEA)
    public String apiIgnoreException() {
        return teamFacade.apiIgnoreException();
    }

    private String apiFallback(Exception ex) {
        return "Returning the response from fallback as we need to provide a fallback for few errors : " + ex.getMessage();
    }

}
