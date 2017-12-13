package com.dataart.service;

import com.dataart.model.MarkerImage;
import com.dataart.repository.MarkerImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Роман on 13.12.2017.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MarkerImageServiceImpl implements MarkerImageService {

    @Autowired
    MarkerImageRepository markerImageRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveImage(MarkerImage image) {
        markerImageRepository.saveAndFlush(image);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteImageById(long id) {
        MarkerImage markerImage = markerImageRepository.findOne(id);
        markerImage.setExist(false);
        markerImageRepository.saveAndFlush(markerImage);
    }

    @Override
    public MarkerImage findById(long id) {
        MarkerImage markerImage = markerImageRepository.findOne(id);
        if(markerImage.getExist()){
            return markerImage;
        }
        return null;
    }

    @Override
    public List<MarkerImage> getMarkerImagesByMarkerId(long markerId) {
        List<MarkerImage> list = markerImageRepository.getMarkerImagesBuMarkerId(markerId)
                .stream()
                .filter(m->m.getExist()==true)
                .limit(5) //because due to arrengment with team (for one marker - 5 images)
                .collect(Collectors.toList());

        return list;
    }
}
