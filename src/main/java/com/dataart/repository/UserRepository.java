package com.dataart.repository;

import com.dataart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Роман on 29.10.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);//Optional<User>

    //@Query("SELECT u FROM com.dataart.entity.User u LEFT JOIN FETCH u.headers WHERE u.toId = :id")
    //User getSenderById(@Param("id") Long id);

    //@Query("SELECT u FROM com.dataart.entity.User u LEFT JOIN FETCH u.headers WHERE u.fromId = :id")
    //User getReceiverById(@Param("id") Long id);
}
