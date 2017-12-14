package com.dataart.service;

import com.dataart.entity.Header;
import com.dataart.entity.Message;
import com.dataart.repository.HeaderRepository;
import com.dataart.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Роман on 23.11.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HeaderServiceImpl implements HeaderService{

    @Autowired
    HeaderRepository headerRepository;

    @Autowired
    MessageRepository messageRepository;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Header findById(long id) {
        return headerRepository.findOne(id);
    }

    @Override
    public void saveHeader(Header header) {
        headerRepository.saveAndFlush(header);
    }

    @Override
    public void updateHeader(Header header) {
        headerRepository.saveAndFlush(header);
    }

    @Override
    public void deleteHeaderById(long id) {
        headerRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        headerRepository.deleteAll();
    }

    @Override
    public List<Message> messagesByHeaderId(long id) {
        return messageRepository.getMessagesByHeaderId(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Header> getHeadersIncomingByUserId(Long id) {
        return headerRepository.getHeadersByToId(id);
        //Query query = (Query) entityManagerFactory.createEntityManager().createQuery(
        //      "SELECT h FROM headers h WHERE toId = " + id);

//        query.setParameter('name', name);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Header> getHeadersOutgoingByUserId(Long id) {
        return headerRepository.getHeadersByFromId(id);
    }
}
