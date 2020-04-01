package com.sk.learn.service;

public interface IdeaService {

    String apiFailureResponse();

    String apiFailureWithFallback();

    String apiSuccessResponse();

    String apiSuccessWithException();

    String apiIgnoreException();
}
