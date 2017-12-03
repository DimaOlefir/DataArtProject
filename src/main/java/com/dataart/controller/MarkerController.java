package com.dataart.controller;

import com.dataart.dto.MarkerDTO;
import com.dataart.model.Marker;
import com.dataart.model.User;
import com.dataart.model.enums.AccessMarker;
import com.dataart.service.MarkerService;
import com.dataart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Роман on 03.12.2017.
 */
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:8090"},
        maxAge = 4800)
@RestController
@RequestMapping("/api")
public class MarkerController extends BaseController{

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    MarkerService markerService;

    @RequestMapping(value = "/marker", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody MarkerDTO markerDTO) {

        User user = userService.findById(getUserId());
        AccessMarker accessMarker = AccessMarker.values()[markerDTO.getAccess()];

        Marker marker = new Marker(user,
                null,
                markerDTO.getName(),
                markerDTO.getDescription(),
                markerDTO.getDateTime(),
                markerDTO.getLat(),
                markerDTO.getLng(),
                accessMarker);

        markerService.saveMarker(marker);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }



}
