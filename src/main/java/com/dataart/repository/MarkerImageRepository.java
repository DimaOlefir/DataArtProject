package com.dataart.repository;

import com.dataart.model.MarkerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Роман on 13.12.2017.
 */
public interface MarkerImageRepository extends JpaRepository<MarkerImage, Long> {

    @Query("SELECT m FROM com.dataart.model.MarkerImage m WHERE markerId = ?1")
    List<MarkerImage> getMarkerImagesBuMarkerId(long id);
}
