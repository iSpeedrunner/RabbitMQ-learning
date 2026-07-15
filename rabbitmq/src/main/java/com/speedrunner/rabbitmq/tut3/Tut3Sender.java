package com.speedrunner.rabbitmq.tut3;


import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class Tut3Sender {

    @Autowired
    private RabbitTemplate rabbit;

    @Autowired
    private FanoutExchange fanout;

    private AtomicInteger dots = new AtomicInteger(0);
    private AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 5000, initialDelay = 500)
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
        rabbit.convertAndSend(fanout.getName(), "", message);
        System.out.println(" [X] Sent '" + message + "'");
    }
}
