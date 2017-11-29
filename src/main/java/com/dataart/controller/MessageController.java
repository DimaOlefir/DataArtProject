package com.dataart.controller;

import com.dataart.dto.MessageDTO;
import com.dataart.model.Header;
import com.dataart.model.Message;
import com.dataart.model.User;
import com.dataart.model.enums.Status;
import com.dataart.service.HeaderService;
import com.dataart.service.MessageService;
import com.dataart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Роман on 24.11.2017.
 */
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:8090"},
        maxAge = 4800)
@RestController
@RequestMapping("/api")
public class MessageController extends BaseController{

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @Autowired
    HeaderService headerService;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody MessageDTO messageDTO) {
        if(messageDTO.getIdFromUser()!=getUserId()){
            return new ResponseEntity <>(HttpStatus.BAD_REQUEST);
        }

        //System.out.println(messageDTO.getDateTime());

        User userFromMsg = userService.findById(messageDTO.getIdFromUser());
        User userToMsg = userService.findById(messageDTO.getIdToUser());
        Header header = new Header(messageDTO.getContent(), Status.EXISTBOTH, messageDTO.getDateTime(),userFromMsg,userToMsg);

        Message message = new Message(true,messageDTO.getContent(),messageDTO.getDateTime(),false,header);

        //System.out.println(header.getDateTime());

        headerService.saveHeader(header);
        messageService.saveMessage(message);

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = new ResponseEntity<>(headers, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/outmessage", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Map<String, String>>> getOutgoingMessages() {
        User user = userService.findById(getUserId());
        List <Header> outGoingMessages = userService.getHeadersOutgoingByUserId(user.getId());

        List <Map<String, String>> list = new ArrayList<>();

        for(Header header : outGoingMessages){
            Map<String, String> body = new HashMap<>();
            body.put("userFromId",String.valueOf(user.getId()));
            body.put("userFromName",user.getFirstName());
            body.put("userFromSurname",user.getLastName());
            body.put("userToId",String.valueOf(header.getUserToMsg().getId()));
            body.put("userToName",header.getUserToMsg().getFirstName());
            body.put("userToSurname",header.getUserToMsg().getLastName());
            body.put("subject",header.getSubject());
            body.put("datetime",String.valueOf(header.getDateTime()));

            list.add(body);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/inmessage", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Map<String, String>>> getIncomingMessages() {
        User user = userService.findById(getUserId());
        List <Header> incomingMessages = userService.getHeadersIncomingByUserId(user.getId());
        List <Map<String, String>> list = new ArrayList<>();

        for(Header header : incomingMessages){
            Map<String, String> body = new HashMap<>();
            body.put("userFromId",String.valueOf(header.getUserFromMsg().getId()));
            body.put("userFromName",header.getUserFromMsg().getFirstName());
            body.put("userFromSurname",header.getUserFromMsg().getLastName());
            body.put("userToId",String.valueOf(user.getId()));
            body.put("userToName",user.getFirstName());
            body.put("userToSurname",user.getLastName());
            body.put("subject",header.getSubject());
            body.put("datetime",String.valueOf(header.getDateTime()));

            list.add(body);
        }


        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}
