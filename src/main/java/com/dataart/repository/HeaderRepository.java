package com.dataart.repository;

import com.dataart.model.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Роман on 23.11.2017.
 */
@Repository
public interface HeaderRepository extends JpaRepository<Header, Long> {

}
