package com.vbaslak.issuetracker.api.controller;

import com.vbaslak.issuetracker.domain.User;
import com.vbaslak.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class RegistrationControllerRest {
    private final UserService userService;

    @Autowired
    RegistrationControllerRest(){
        userService = new UserService();
    }

    @GetMapping()
    public String greeting() {
        return "Hello user";
    }

    @PostMapping("/login")
    public String loginUser(
        @RequestBody String username,
        @RequestBody String password
    ){
        return greeting();
    }


    @PostMapping("/registration")
    public User addUser(
            @RequestBody String username,
            @RequestBody String password
    ){
        return userService.addUserRest(username, password);
    }
}
