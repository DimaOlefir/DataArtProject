package com.dataart.controller;

import com.dataart.model.Marker;
import com.dataart.model.MarkerImage;
import com.dataart.service.MarkerImageService;
import com.dataart.service.MarkerService;
import com.dataart.service.S3Service;
import com.dataart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Роман on 13.12.2017.
 */
@RestController
@RequestMapping("/api")
public class ImageController extends BaseController{

    @Autowired
    UserService userService;

    @Autowired
    S3Service s3Service;

    @Autowired
    MarkerService markerService;

    @Autowired
    MarkerImageService markerImageService;

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestParam MultipartFile multipartFile, String markerId) throws IOException {
        File file = null;
        try {
            System.out.println("tre convert the multipartfile to file");
            file = convert(multipartFile);
        }
        catch (IOException e){
            e.toString();
        }
        if (file!=null) {
            System.out.println("create the path " + markerId);

            String path = markerId + "marker" + file.getName();
            s3Service.saveMarkerImageByMarkerId(file,path);

            System.out.println("saved file to amazon");

            Marker marker = markerService.findById(Long.getLong(markerId));
            MarkerImage markerImage = new MarkerImage(path,true, marker);
            markerImageService.saveImage(markerImage);

            System.out.println("saved markerImage to database");

            /*String fileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();*/


            //Files.copy(is, Paths.get(path + fileName),
            //      StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
