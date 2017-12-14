package com.dataart.repository;

import com.dataart.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Роман on 23.11.2017.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT h FROM com.dataart.entity.Message h WHERE headerId = ?1")
    List<Message> getMessagesByHeaderId(long id);
}
