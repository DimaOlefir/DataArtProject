package com.dataart.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.dataart.model.MarkerImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Роман on 11.12.2017.
 */
@Service
public class S3ServiceImpl implements S3Service {

    private final String bucket = "bucket-s3-first";

    @Autowired
    private AmazonS3 s3client;

    @Override
    public void saveMarkerImageByMarkerId(File file, String path) {
        s3client.putObject(bucket,path ,file);
    }

    @Override
    public byte[] getMarkerImagesByMarkerId(String path) throws IOException {
        S3Object s3object = s3client.getObject(bucket,path);
        S3ObjectInputStream inputStream = s3object.getObjectContent();//S3ObjectInputStream

        byte[] buffer = new byte[inputStream.available()];

        return buffer;
    }
}
