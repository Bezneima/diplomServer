package com.testSpring.Blog.controllers.Tinder;

import com.testSpring.Blog.tinder.TinderRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TinderControllers {



    @PostMapping("/TinderLike")
    public String tinderLike(@RequestParam(defaultValue = "") String id) throws IOException {
        TinderRest tinderRest = new TinderRest();
        return tinderRest.EstimatePerson(id, true);
    }

    @PostMapping("/TinderDislike")
    public String tinderDislike(@RequestParam(defaultValue = "") String id) throws IOException {
        TinderRest tinderRest = new TinderRest();
        return tinderRest.EstimatePerson(id, false);
    }

    @GetMapping("/GetAllPeopleFromTinder")
    public String showAllPeopleFromTinder() throws IOException {
        TinderRest tinderRest = new TinderRest();
        return tinderRest.returnPeopleNearby();
    }

    /*@GetMapping("/GetAllTinder")
    public String blogMain(Model model) throws IOException {
        TinderRest tinderRest = new TinderRest();
        TinderPeopleNearBy tinderPeopleNearBy = tinderRest.returnPeopleNearby();
        model.addAttribute("UserName", tinderPeopleNearBy.getData().getResults().get(0).getUser().getName());
        model.addAttribute("Photos", tinderRest.getPhotoFromUser(tinderPeopleNearBy.getData().getResults().get(0).getUser().getPhotos()));
        model.addAttribute("Age", tinderPeopleNearBy.getData().getResults().get(0).getUser().getBirthDate().substring(0, 10));
        model.addAttribute("Bio", tinderPeopleNearBy.getData().getResults().get(0).getUser().getBio());
        model.addAttribute("Id", tinderPeopleNearBy.getData().getResults().get(0).getUser().getId());
        return "blog-main";
    }*/
}
