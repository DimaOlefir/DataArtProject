package com.dataart.config;

import com.dataart.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Created by Роман on 13.11.2017.
 */
//@Configuration
public class SpringSecurityBeans {
    /*@Autowired
    UserDetailsService userDetailsService;

    @Bean
    public CsrfTokenRepository customCsrfTokenRepository() {

        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");

        return repository;
    }

    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {

        TokenBasedRememberMeServices service = new TokenBasedRememberMeServices("DEVELNOTES_REMEMBER_TOKEN",
                userDetailsService);

        service.setCookieName("DEVELNOTES_REMEMBER_ME_COOKIE");
        service.setUseSecureCookie(false);
        service.setAlwaysRemember(false);

        return service;
    }

    @Bean
    public Http403ForbiddenEntryPoint http403ForbiddenEntryPoint() {
        return new Http403ForbiddenEntryPoint();
    }*/

}
