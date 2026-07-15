package com.speedrunner.rabbitmq.service;

import lombok.Setter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Setter
public class Tut2Sender {

    @Autowired
    private RabbitTemplate rabbit;

    @Autowired
    private Queue queue;

    AtomicInteger dots = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void send() {
        StringBuilder builder = new StringBuilder("hello");

        if(dots.incrementAndGet() == 4) {
            dots.set(1);
        }

        for(int i = 0; i <= dots.get(); i++) {
            builder.append(".");
        }

        builder.append(count.incrementAndGet());
        String message = builder.toString();
        rabbit.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }

}
