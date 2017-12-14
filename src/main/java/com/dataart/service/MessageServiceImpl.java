package com.dataart.service;

import com.dataart.entity.Message;
import com.dataart.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Роман on 23.11.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageRepository messageRepository;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Message findById(long id) {
        return messageRepository.getOne(id);
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.saveAndFlush(message);
    }

    @Override
    public void updateMessage(Message message) {
        messageRepository.saveAndFlush(message);
    }

    @Override
    public void deleteById(long id) {
        messageRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        messageRepository.deleteAll();
    }
}
