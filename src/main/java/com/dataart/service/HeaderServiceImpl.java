package com.dataart.service;

import com.dataart.model.Header;
import com.dataart.repository.HeaderRepository;
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

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Header findById(long id) {
        return headerRepository.getOne(id);
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

}
