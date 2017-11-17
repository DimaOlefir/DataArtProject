package com.dataart.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Роман on 15.11.2017.
 */
public class JwtTokenMissingException extends AuthenticationException {
    public JwtTokenMissingException(String s) {
        super(s);
    }
}
