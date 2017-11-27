package com.dataart.security;

import com.dataart.model.User;
import com.dataart.security.util.JwtUtil;
import com.dataart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Роман on 17.11.2017.
 */
@Component
public class UserAuthentication {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public UserAuthentication(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public String attemptAuthentication(String login, String password) {
        User checkedUser = userService.findByLogin(login);
        String token = null;
        if (checkedUser != null &&
                bCryptPasswordEncoder.matches(password,checkedUser.getPassword())) {
            token = jwtUtil.generateToken(checkedUser);
            }
        return token;
    }

}

