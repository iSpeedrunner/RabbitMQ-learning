package com.speedrunner.rabbitmq_receiver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitService {

    @RabbitListener(queues = {"FirstQueue"})
    public void receive(String message) {
        log.info(message + " - got from queue");
    }
}