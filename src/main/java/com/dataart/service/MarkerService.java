package com.dataart.service;

import com.dataart.entity.Marker;

import java.util.List;

/**
 * Created by Роман on 02.12.2017.
 */
public interface MarkerService {

    Marker findById(long id);

    void saveMarker(Marker marker);

    void updateMarker(Marker marker);

    void deleteMarkerById (long id);

    List <Marker> getAllPublicMarkers();

    List <Marker> getAllMarkersByUserId(long id);

    List <Marker> getMarkersForFriendsByUserId(long id);

    List <Marker> getMarkersForAllUsersByUserId(long id);
}
