package com.sk.learn.resource;

import com.sk.learn.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team/")
public class TeamResource {

    @Autowired
    private TeamService teamService;

    @GetMapping()
    public String getTeamDetails () {
        return "Success";
    }

    @GetMapping("failure")
    public String validateApiFailure(){
        return teamService.apiFailureResponse();
    }

    @GetMapping("success")
    public String validateApiSuccess(){
        return teamService.apiSuccessResponse();
    }

    @GetMapping("successException")
    public String getSuccessResponseWithException(){
        return teamService.apiSuccessWithException();
    }

    @GetMapping("ignore")
    public String ignoreException(){
        return teamService.apiIgnoreException();
    }

    @GetMapping("fallback")
    public String failureWithFallback() {
        return teamService.apiFailureWithFallback();
    }
}
