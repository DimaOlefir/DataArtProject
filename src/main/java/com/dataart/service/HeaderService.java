package com.dataart.service;

import com.dataart.entity.Header;
import com.dataart.entity.Message;

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

    List<Header> getHeadersIncomingByUserId(Long id);

    List<Header> getHeadersOutgoingByUserId(Long id);

}
