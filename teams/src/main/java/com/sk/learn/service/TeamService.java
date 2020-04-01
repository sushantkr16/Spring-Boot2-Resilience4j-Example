package com.sk.learn.service;

public interface TeamService {

    String apiFailureResponse();

    String apiFailureWithFallback();

    String apiSuccessResponse();

    String apiSuccessWithException();

    String apiIgnoreException();
}
