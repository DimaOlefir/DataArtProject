package com.dataart.repository;

import com.dataart.model.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Роман on 02.12.2017.
 */
@Repository
public interface MarkerRepository extends JpaRepository<Marker, Long> {

    @Query("SELECT m FROM com.dataart.model.Marker m WHERE userId = ?1")
    List<Marker> getMarkersBuUserId(long id);

    @Query("SELECT m FROM com.dataart.model.Marker m WHERE access = ?1 OR access = ?2")
    List<Marker> getMarkersBuUserId(String allusers, String friends);
}
