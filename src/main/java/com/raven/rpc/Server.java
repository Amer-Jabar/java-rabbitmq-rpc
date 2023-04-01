package com.raven.rpc;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.raven.rpc.RPCConfig.RECEIVING_QUEUE;
import static com.raven.rpc.RPCConfig.REPLY_EXCHANGE;

@Component
public class Server {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RECEIVING_QUEUE)
    public void receive(@Payload Message message) {
        System.out.println("Reading message at server");
        System.out.println("Sending message from server to reply exchange");
        rabbitTemplate.send(REPLY_EXCHANGE, "", new Message("message here".getBytes()), new CorrelationData("1"));
    }
}
