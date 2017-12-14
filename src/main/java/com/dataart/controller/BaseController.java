package com.dataart.controller;

import com.dataart.entity.User;
import com.dataart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Роман on 24.11.2017.
 */
public class BaseController {

    @Autowired
    private UserService service;

    protected Long getUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        User user = service.findByLogin(login);
        return user.getId();
    }
}
