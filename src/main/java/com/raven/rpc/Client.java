package com.raven.rpc;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import static com.raven.rpc.RPCConfig.REPLY_QUEUE;

@Configuration
@EnableScheduling
public class Client {

    @Autowired
    ClientEventPublisher rpcEventPublisher;

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        System.out.println("Sending message from client to receiving exchange");
        rpcEventPublisher.sendMessage(new Message("message here".getBytes()));
    }

    @RabbitListener(queues = REPLY_QUEUE)
    public void receive(@Payload Message message) {
        System.out.println("Receive message at client");
        System.out.println("\n\n");
    }
}
