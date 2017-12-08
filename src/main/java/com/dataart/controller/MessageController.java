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
import org.springframework.core.env.SystemEnvironmentPropertySource;
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
/*@CrossOrigin(//origins = {"http://localhost:8080","http://localhost:8090"},
        maxAge = 4800)*/
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
        Header header = new Header(messageDTO.getContent(), Status.EXISTBOTH, messageDTO.getDateTime(),userFromMsg,userToMsg);

        Message message = new Message(true,messageDTO.getContent(),messageDTO.getDateTime(),false,header);

        //System.out.println(header.getDateTime());

        headerService.saveHeader(header);
        messageService.saveMessage(message);

    //    HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(value = "/outmessage", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Map<String, String>>> getOutgoingMessages() {
        User user = userService.findById(getUserId());
        List <Header> outGoingMessages = headerService.getHeadersOutgoingByUserId(user.getId());

        List <Map<String, String>> list = new ArrayList<>();

        for(Header header : outGoingMessages){
            if(header.getStatus()!=Status.REMOVESENDER & header.getStatus()!=Status.REMOVEBOTH) {
                Map<String, String> body = new HashMap<>();
                body.put("userFromId", String.valueOf(user.getId()));
                body.put("userFromName", user.getFirstName());
                body.put("userFromSurname", user.getLastName());
                body.put("userToId", String.valueOf(header.getUserToMsg().getId()));
                body.put("userToName", header.getUserToMsg().getFirstName());
                body.put("userToSurname", header.getUserToMsg().getLastName());
                body.put("subject", header.getSubject());
                body.put("datetime", String.valueOf(header.getDateTime()));
                body.put("headerId", String.valueOf(header.getId()));
                list.add(body);
            }
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/inmessage", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Map<String, String>>> getIncomingMessages() {
        User user = userService.findById(getUserId());
        List <Header> incomingMessages = headerService.getHeadersIncomingByUserId(user.getId());
        List <Map<String, String>> list = new ArrayList<>();

        for(Header header : incomingMessages){
            if(header.getStatus()!=Status.REMOVERECEIVER & header.getStatus()!=Status.REMOVEBOTH) {
                Map<String, String> body = new HashMap<>();
                body.put("userFromId", String.valueOf(header.getUserFromMsg().getId()));
                body.put("userFromName", header.getUserFromMsg().getFirstName());
                body.put("userFromSurname", header.getUserFromMsg().getLastName());
                body.put("userToId", String.valueOf(user.getId()));
                body.put("userToName", user.getFirstName());
                body.put("userToSurname", user.getLastName());
                body.put("subject", header.getSubject());
                body.put("datetime", String.valueOf(header.getDateTime()));
                body.put("headerId", String.valueOf(header.getId()));
                list.add(body);
            }
        }


        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, String>> getMessageById(@PathVariable("id") Long id) {
        Header header = headerService.findById(id);

        if(header==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //System.out.println("header  :" + header);
        User sender = header.getUserFromMsg(); //userService.getSenderByHeaderId(header.getId());
        User receiver = header.getUserToMsg();

        /*if(!(getUserId()!=sender.getId()||getUserId()!=receiver.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/

        if(getUserId().equals(sender.getId())){
            if(header.getStatus()==Status.REMOVESENDER||
                    header.getStatus()==Status.REMOVEBOTH){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        else if(getUserId().equals(receiver.getId())){
            if(header.getStatus()==Status.REMOVERECEIVER||
                    header.getStatus()==Status.REMOVEBOTH){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String content = headerService.messagesByHeaderId(header.getId()).get(0).getContent();//index = 0, because in list we have only one message
        Map<String, String> map = new HashMap<>();

        map.put("userFromId",String.valueOf(sender.getId()));
        map.put("userFromName",sender.getFirstName());
        map.put("userFromSurname",sender.getLastName());
        map.put("userToId",String.valueOf(receiver.getId()));
        map.put("userToName",receiver.getFirstName());
        map.put("userToSurname",receiver.getLastName());
        map.put("content",content);
        map.put("datetime",String.valueOf(header.getDateTime()));

        //if getId()!=header.getUserFromMsg().getId()||header.getUserToMsg().getId()

        /*MessageDTO messageDTO = new MessageDTO(header.getUserFromMsg().getId(),
                header.getUserToMsg().getId(),
                content,
                header.getDateTime());*/

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/message", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deleteMessage(@RequestBody Map<String, Integer> map) {
        Long messageId = Long.valueOf(map.get("messageId"));
        Header header = headerService.findById(messageId);

        if(header==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User sender = header.getUserFromMsg();
        User receiver = header.getUserToMsg();

        /*if(!(getUserId()!=sender.getId()||getUserId()!=receiver.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/

        if(getUserId().equals(sender.getId())){
            if(header.getStatus()==Status.REMOVERECEIVER){
                header.setStatus(Status.REMOVEBOTH);
            }
            header.setStatus(Status.REMOVESENDER);
        }
        else if(getUserId().equals(receiver.getId())){
            if(header.getStatus()==Status.REMOVESENDER){
                header.setStatus(Status.REMOVEBOTH);
            }
            header.setStatus(Status.REMOVERECEIVER);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        headerService.updateHeader(header);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}


