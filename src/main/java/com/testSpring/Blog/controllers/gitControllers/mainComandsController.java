package com.testSpring.Blog.controllers.gitControllers;

import com.testSpring.Blog.controllers.gitUtils.pojos.Branches;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.testSpring.Blog.controllers.gitUtils.FileSystemUtils.loadHistorySettings;
import static com.testSpring.Blog.controllers.gitUtils.FileSystemUtils.makeJSONString;

@RestController
public class mainComandsController {
    @GetMapping("/authorization")
    public String authorization(@RequestParam(defaultValue = "") String login, @RequestParam(defaultValue = "") String password) throws IOException {
        if (login.equals("qwe") && password.equals("123"))
            return "true";
        else
            return "false";
    }

    @GetMapping("/getBrancesJSON")
    public String getBrancesJSON() {
        loadHistorySettings();
        Branches branches = Branches.getInstance();
        return makeJSONString(branches);
    }

}
