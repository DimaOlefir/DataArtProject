package com.dataart.repository;

import com.dataart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Роман on 29.10.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);//Optional<User>
}
