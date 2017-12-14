package com.dataart.repository;

import com.dataart.entity.CommentMarker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Роман on 05.12.2017.
 */
public interface CommentMarkerRepository extends JpaRepository<CommentMarker, Long> {

@Query("SELECT c FROM com.dataart.entity.CommentMarker c WHERE markerId = ?1")
List<CommentMarker> getCommentsMarkerBuMarkerId(long id);
}
