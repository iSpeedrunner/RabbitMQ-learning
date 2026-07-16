package com.speedrunner.rabbitmq.tut4;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class Tut4Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;

    AtomicInteger index = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {"orange", "black", "green"};

    @Scheduled(initialDelay = 1000, fixedDelay = 2000)
    public void send() {
        StringBuilder builder = new StringBuilder("hello to ");

        if(this.index.incrementAndGet() == 3) {
            this.index.set(0);
        }

        String key = keys[this.index.get()];

        builder.append(key).append(' ').append(this.count.incrementAndGet());
        String message = builder.toString();

        template.convertAndSend(direct.getName(), key, message);

        System.out.println(" [x] Sent '" + message + "' with routing key: " + key);
    }

}
