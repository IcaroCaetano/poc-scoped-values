package com.myprojecticaro.poc_scoped_values.api.controller;

import com.myprojecticaro.poc_scoped_values.api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService = new UserService();

    @GetMapping("/users")
    public String getUser() {
        userService.process();
        return "ok";
    }
}