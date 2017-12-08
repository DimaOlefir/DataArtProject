package com.dataart.controller;

import com.dataart.dto.CommentMarkerDTO;
import com.dataart.dto.MarkerDTO;
import com.dataart.model.CommentMarker;
import com.dataart.model.Marker;
import com.dataart.model.User;
import com.dataart.model.enums.AccessMarker;
import com.dataart.service.CommentMarkerService;
import com.dataart.service.MarkerService;
import com.dataart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Роман on 03.12.2017.
 */
/*@CrossOrigin(//origins = {"http://localhost:8080","http://localhost:8090"},
        maxAge = 4800)*/
@RestController
@RequestMapping("/api")
public class MarkerController extends BaseController{

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    MarkerService markerService;

    @Autowired
    CommentMarkerService commentMarkerService;


    @RequestMapping(value = "/marker", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createMarker(@RequestBody MarkerDTO markerDTO) {

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

        //HttpHeaders headers = new HttpHeaders();
        //return new ResponseEntity<>(headers, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/mymarkers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Marker>> getOnlyForUserMarkers(){
        List<Marker> markers = markerService.getAllMarkersByUserId(getUserId());
        if(markers==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(markers, HttpStatus.OK);
    }

    @RequestMapping(value = "/frindmarkers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Marker>> getMarkersForFriends(){
        List<Marker> markers = markerService.getMarkersForFriendsByUserId(getUserId());
        if(markers==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(markers, HttpStatus.OK);
    }

    @RequestMapping(value = "/publicmarkers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Marker>> getMarkersForAllUser(){
        List<Marker> markers = markerService.getMarkersForAllUsersByUserId(getUserId());
        if(markers==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(markers, HttpStatus.OK);
    }

    @RequestMapping(value = "/marker/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Marker> getMarkerById(@PathVariable("id") Long id) {
        Marker marker = markerService.findById(id);
        if(marker==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(marker.getUser().getId()!=getUserId()) {
            if (marker.getAccessMarker() != AccessMarker.ALLUSERS){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(marker, HttpStatus.OK);
    }

    @RequestMapping(value = "/marker/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> updateMarker(@PathVariable("id") Long id, @RequestBody MarkerDTO markerDTO) {

        Marker marker = markerService.findById(id);
        if(marker==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(marker.getUser().getId()!=getUserId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //User user = userService.findById(getUserId());
        AccessMarker accessMarker = AccessMarker.values()[markerDTO.getAccess()];

        marker.setName(markerDTO.getName());
        marker.setDescription(markerDTO.getDescription());
        marker.setDateTime(markerDTO.getDateTime());
        marker.setAccessMarker(accessMarker);
        marker.setLat(markerDTO.getLat());
        marker.setLng(markerDTO.getLng());

        markerService.updateMarker(marker);

        //HttpHeaders headers = new HttpHeaders();
        //return new ResponseEntity<>(headers, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/marker/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deleteMarkerById(@PathVariable("id") Long id) {
        Marker marker = markerService.findById(id);
        if(marker==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(marker.getUser().getId()!=getUserId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        markerService.deleteMarkerById(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/marker/comment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createComment(@RequestBody CommentMarkerDTO commentMarkerDTO) {
        User user = userService.findById(getUserId());
        Marker marker = markerService.findById(commentMarkerDTO.getMarkerId());
        if(marker==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(marker.getAccessMarker()==AccessMarker.ME & marker.getUser().getId()!=user.getId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CommentMarker commentMarker = new CommentMarker(
                user,
                marker,
                commentMarkerDTO.getDateTime(),
                commentMarkerDTO.getText()
        );

        commentMarkerService.saveCommentMarker(commentMarker);

        //HttpHeaders headers = new HttpHeaders();
        //return new ResponseEntity<>(headers, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/marker/comment/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> updateMarkerComment(@PathVariable("id") Long id, @RequestBody CommentMarkerDTO commentMarkerDTO){

        User user = userService.findById(getUserId());
        CommentMarker commentMarker = commentMarkerService.findById(id);
        if(commentMarker==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Marker marker = markerService.findById(commentMarkerDTO.getMarkerId());
        if(marker==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(marker.getAccessMarker()==AccessMarker.ME & marker.getUser().getId()!=user.getId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        commentMarker.setDateTime(commentMarkerDTO.getDateTime());
        commentMarker.setText(commentMarkerDTO.getText());

        commentMarkerService.updateCommentMarker(commentMarker);

        //HttpHeaders headers = new HttpHeaders();
        //return new ResponseEntity<>(headers, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/marker/{id}/comments", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<CommentMarker>> getCommentsByMarkerId(@PathVariable("id") Long id){

        Marker marker = markerService.findById(id);
        if(marker==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(marker.getAccessMarker()==AccessMarker.ME & marker.getUser().getId()!=getUserId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List <CommentMarker> list = commentMarkerService.getAllCommentsMarkerByMarkerId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @RequestMapping(value = "/marker/comment", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deleteComment(@RequestBody Map<String, Integer> map) {
        CommentMarker commentMarker = commentMarkerService.findById(Long.valueOf(map.get("commentId")));
        if(commentMarker==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Marker marker = commentMarker.getMarker();
        if(marker==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userService.findById(getUserId());
        if(commentMarker.getCreator().getId()!=user.getId() & marker.getUser().getId()!=user.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        commentMarkerService.deleteCommentMarkerById(commentMarker.getId());

        //HttpHeaders headers = new HttpHeaders();
        //return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);

        //HttpHeaders headers = new HttpHeaders();
        //return new ResponseEntity<>(headers, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
