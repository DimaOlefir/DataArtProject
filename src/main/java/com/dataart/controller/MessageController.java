package com.dataart.controller;

import com.dataart.service.HeaderService;
import com.dataart.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Роман on 24.11.2017.
 */

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    MessageService messageService;

    @Autowired
    HeaderService headerService;


}
