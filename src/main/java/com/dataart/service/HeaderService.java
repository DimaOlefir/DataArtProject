package com.dataart.service;

import com.dataart.model.Header;
import com.dataart.model.Message;

import java.util.List;

/**
 * Created by Роман on 23.11.2017.
 */
public interface HeaderService {

    Header findById(long id);

    void saveHeader(Header header);

    void updateHeader(Header header);

    void deleteHeaderById(long id);

    void deleteAll();

    List<Message> messagesByHeaderId(long id);
    //List <Header> findAllHeaders();

}