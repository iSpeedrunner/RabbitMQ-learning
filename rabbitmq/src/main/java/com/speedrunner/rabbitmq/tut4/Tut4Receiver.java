package com.speedrunner.rabbitmq.tut4;


import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Tut4Receiver {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String in) {
        System.out.println("Instance 1 [x] Received '" + in + "'");
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String in) {
        System.out.println("Instance 2 [x] Received '" + in + "'");
    }
}
