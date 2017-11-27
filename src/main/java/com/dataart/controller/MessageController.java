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

        User userFromMsg = userService.findById(messageDTO.getIdFromUser());
        User userToMsg = userService.findById(messageDTO.getIdToUser());
        Header header = new Header("subject", Status.EXISTBOTH, messageDTO.getDateTime(),userFromMsg,userToMsg);

        Message message = new Message(true,messageDTO.getContent(),messageDTO.getDateTime(),false,header);
        messageService.saveMessage(message);

        headerService.saveHeader(header);

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = new ResponseEntity<>(headers, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Map<String, String>>> getIncomingMessages() {
        User user = userService.findById(getUserId());
        List <Header> incomingMessages = user.getHeadersIncoming();
        Map<String, String> body = new HashMap<>();
        body.put("1","sdf");
        body.put("2","czvvv");

        Map<String, String> body1 = new HashMap<>();
        body.put("1","fsdfsdf");
        body.put("2","czvvv");
        List <Map<String, String>> list = new ArrayList<>();
        list.add(body);
        list.add(body1);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}
