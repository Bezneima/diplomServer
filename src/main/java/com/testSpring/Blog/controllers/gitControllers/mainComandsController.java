package com.testSpring.Blog.controllers.gitControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class mainComandsController {
    @GetMapping("/authorization")
    public String authorization(@RequestParam(defaultValue = "") String login, @RequestParam(defaultValue = "") String password) throws IOException {
        if (login.equals("qwe") && password.equals("123"))
            return "true";
        else
            return "false";
    }
}
