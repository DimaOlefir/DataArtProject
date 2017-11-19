package com.dataart.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Роман on 17.11.2017.
 */
public class JwtTokenMalformedException extends AuthenticationException {

    public JwtTokenMalformedException(String msg) {
        super(msg);
    }

}

