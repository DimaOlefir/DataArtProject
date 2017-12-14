package com.dataart.service;

import com.dataart.entity.Marker;
import com.dataart.entity.enums.AccessMarker;
import com.dataart.repository.MarkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Роман on 02.12.2017.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MarkerServiceImpl implements MarkerService{

    @Autowired
    MarkerRepository markerRepository;

    @Override
    public Marker findById(long id) {
        return markerRepository.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveMarker(Marker marker) {
        markerRepository.saveAndFlush(marker);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateMarker(Marker marker) {
        markerRepository.saveAndFlush(marker);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteMarkerById(long id) {
        markerRepository.delete(id);
    }

    @Override
    public List<Marker> getAllPublicMarkers() {
        //System.out.println("------------------- AccessMarker.ALLUSERS.toString() ----" + AccessMarker.ALLUSERS.toString());
        return markerRepository.getMarkersBuUserId(AccessMarker.ALLUSERS.toString(),AccessMarker.FRIENDS.toString());
    }

    @Override
    public List<Marker> getAllMarkersByUserId(long id) {
        /*List <Marker> markers = markerRepository.getMarkersBuUserId(id)
                .stream()
                .filter(m -> m.getAccessMarker()== AccessMarker.ME)
                .collect(Collectors.toList());
        return markers;*/
        return markerRepository.getMarkersBuUserId(id);
    }

    @Override
    public List<Marker> getMarkersForFriendsByUserId(long id) {
        List <Marker> markers = markerRepository.getMarkersBuUserId(id)
                .stream()
                .filter(m -> m.getAccessMarker()== AccessMarker.FRIENDS || m.getAccessMarker()== AccessMarker.ALLUSERS)
                .collect(Collectors.toList());
        return markers;
    }

    @Override
    public List<Marker> getMarkersForAllUsersByUserId(long id) {
        List <Marker> markers = markerRepository.getMarkersBuUserId(id)
                .stream()
                .filter(m -> m.getAccessMarker()== AccessMarker.ALLUSERS)
                .collect(Collectors.toList());
        return markers;
    }
}
