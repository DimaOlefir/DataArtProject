package com.dataart.service;

import com.dataart.entity.Message;

/**
 * Created by Роман on 23.11.2017.
 */
public interface MessageService {

    Message findById(long id);

    void saveMessage(Message message);

    void updateMessage(Message message);

    void deleteById(long id);

    void deleteAll();

}
