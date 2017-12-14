package com.dataart.service;

import com.dataart.entity.CommentMarker;
import com.dataart.repository.CommentMarkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Роман on 05.12.2017.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommentMarkerServiceImpl implements CommentMarkerService{

    @Autowired
    CommentMarkerRepository commentMarkerRepository;

    @Override
    public CommentMarker findById(long id) {
        return commentMarkerRepository.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveCommentMarker(CommentMarker commentMarker) {
        commentMarkerRepository.saveAndFlush(commentMarker);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateCommentMarker(CommentMarker commentMarker) {
        commentMarkerRepository.saveAndFlush(commentMarker);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteCommentMarkerById(long id) {
        commentMarkerRepository.delete(id);
    }

    @Override
    public List<CommentMarker> getAllCommentsMarkerByMarkerId(long id) {
        return commentMarkerRepository.getCommentsMarkerBuMarkerId(id);
    }
}
