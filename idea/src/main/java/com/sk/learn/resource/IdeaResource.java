package com.sk.learn.resource;

import com.sk.learn.exception.IgnoreException;
import com.sk.learn.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/idea/")
public class IdeaResource {

    @Autowired
    private IdeaService ideaService;

    @GetMapping()
    public String getTeamDetails () {
        return "Success";
    }

    @GetMapping("failure")
    public String validateApiFailure() throws HttpServerErrorException {
        return ideaService.apiFailureResponse();
    }

    @GetMapping("success")
    public String validateApiSuccess() {
        return ideaService.apiSuccessResponse();
    }

    @GetMapping("successException")
    public String getSuccessResponseWithException() throws HttpClientErrorException {
        return ideaService.apiSuccessWithException();
    }

    @GetMapping("ignore")
    public String ignoreException() throws IgnoreException {
        return ideaService.apiIgnoreException();
    }

    @GetMapping("fallback")
    public String failureWithFallback() throws HttpServerErrorException {
        return ideaService.apiFailureWithFallback();
    }
}
