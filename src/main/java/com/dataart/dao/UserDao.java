package com.dataart.dao;

import com.dataart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Роман on 29.10.2017.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
