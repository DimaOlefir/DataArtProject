package com.dataart.repository;

import com.dataart.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Роман on 23.11.2017.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
