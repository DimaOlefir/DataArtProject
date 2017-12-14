package com.dataart.service;

import com.dataart.repository.HeaderRepository;
import com.dataart.repository.UserRepository;
import com.dataart.entity.User;
import com.dataart.entity.enums.Role;
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
    private UserRepository userRepository;

    @Autowired
    private HeaderRepository headerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);//.get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserExistByLogin(String login){//,long id) {
        User user = findByLogin(login);
        /*System.out.println(user.getId());
        return (user!=null)&(user.getId()!=id);*/
        return user!=null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void changePassword(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    //this method I 've used for testing when I 've got the lazy exception
    @Override
    public User getSenderByHeaderId(long id) {
        return headerRepository.findOne(id).getUserFromMsg();
    }


}
