package com.dataart.controller;

import com.dataart.model.User;
import com.dataart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Роман on 06.11.2017.
 */

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/", "/welcome"})
    public String welcome(){
        return "Welcome, user";
    }

    @GetMapping("/login")
    public String login(){
        return "Welcome, user";
    }

    @GetMapping("/reqistration")
    public String reqistration(){
        return "Welcome, user";
    }

    @PostMapping("/reqistration")
    public String reqistration(@RequestBody User user){
        userService.saveUser(user);
        return "redirect:/welcome";
    }

}
