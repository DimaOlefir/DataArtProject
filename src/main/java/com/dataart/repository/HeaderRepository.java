package com.dataart.repository;

import com.dataart.entity.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Роман on 23.11.2017.
 */
@Repository
public interface HeaderRepository extends JpaRepository<Header, Long> {

    //Header getHeaderById(long id);

    @Query("SELECT h FROM com.dataart.entity.Header h WHERE fromId = ?1")
    List<Header> getHeadersByFromId(long id);


    @Query("SELECT h FROM com.dataart.entity.Header h WHERE toId = ?1")
    List<Header> getHeadersByToId(long id);
}
