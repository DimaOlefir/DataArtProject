package com.dataart.service;

import java.io.File;
import java.io.IOException;

/**
 * Created by Роман on 11.12.2017.
 */
public interface S3Service {

    void saveMarkerImageByMarkerId(File file, String path);

    byte[] getMarkerImagesByMarkerId(String path) throws IOException;
}
