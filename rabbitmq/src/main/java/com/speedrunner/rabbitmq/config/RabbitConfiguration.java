package com.speedrunner.rabbitmq.config;

import com.speedrunner.rabbitmq.service.Tut2Receiver;
import com.speedrunner.rabbitmq.service.Tut2Sender;
import lombok.Setter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut2", "work-queues"})
@Setter
@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue taskQueue() {
        return QueueBuilder.durable("task_queue")
                .quorum()
                .build();
    }


    @Profile("receiver")
    @Configuration
    public static class ReceiverConfig {
        @Bean
        public Tut2Receiver receiver1() {
            return new Tut2Receiver(1);
        }

        @Bean
        public Tut2Receiver receiver2() {
            return new Tut2Receiver(2);
        }
    }

    @Profile("sender")
    @Configuration
    public static class SenderConfig {
        @Bean
        public Tut2Sender sender() {
            return new Tut2Sender();
        }
    }

}
