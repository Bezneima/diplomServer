package com.testSpring.Blog.controllers;

import com.google.gson.Gson;
import com.testSpring.Blog.tinder.TinderRest;
import com.testSpring.Blog.tinder.pojos.TinderPeopleNearBy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
public class MainPage {

     @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }



}
