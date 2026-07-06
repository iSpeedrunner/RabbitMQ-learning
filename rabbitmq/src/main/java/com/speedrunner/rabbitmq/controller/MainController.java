package com.speedrunner.rabbitmq.controller;

import com.speedrunner.rabbitmq.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private MessageSender sender;

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody String message) {
        sender.send(message);

        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
