package com.dataart.service;

import com.dataart.model.CommentMarker;

import java.util.List;

/**
 * Created by Роман on 05.12.2017.
 */
public interface CommentMarkerService {

    CommentMarker findById(long id);

    void saveCommentMarker (CommentMarker commentMarker);

    void updateCommentMarker (CommentMarker commentMarker);

    void deleteCommentMarkerById (long id);

    List<CommentMarker> getAllCommentsMarkerByMarkerId(long id);
}
