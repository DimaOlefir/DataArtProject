package com.dataart.service;

import com.dataart.dao.UserDao;
import com.dataart.model.User;
import com.dataart.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Роман on 06.11.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        return userDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return userDao.findByLogin(login).get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userDao.saveAndFlush(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        userDao.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserExist(User user) {
        return findByLogin(user.getLogin())!=null;
    }
}
