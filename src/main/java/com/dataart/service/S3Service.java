package com.dataart.service;

import com.dataart.model.MarkerImage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Роман on 11.12.2017.
 */
public interface S3Service {

    void saveMarkerImageByMarkerId(File file, String path);

    byte[] getMarkerImagesByMarkerId(String path) throws IOException;
}
