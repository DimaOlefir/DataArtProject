package com.dataart.service.security;

import com.dataart.dao.UserDao;
import com.dataart.model.User;
import com.dataart.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * Created by Роман on 29.10.2017.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    //We will find the required user by him login
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        //User user = userDao.findByUsername(username);

       // Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

       // for (Role role : user.getRoles()) {
       //     grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
       // }

//AuthorityUtils.createAuthorityList(user.getRole().toString()));

        User user = userDao.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with login=%s was not found", login)));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
       // return new CurrentUser(user);
    }
}