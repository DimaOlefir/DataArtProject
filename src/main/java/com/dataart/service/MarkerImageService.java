package com.dataart.service;

import com.dataart.model.MarkerImage;

import java.util.List;

/**
 * Created by Роман on 13.12.2017.
 */
public interface MarkerImageService {

    void saveImage(MarkerImage image);

    void deleteImageById(long id);

    MarkerImage findById(long id);

    List<MarkerImage> getMarkerImagesByMarkerId(long markerId);
}
