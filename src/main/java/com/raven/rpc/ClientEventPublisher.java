package com.raven.rpc;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.raven.rpc.RPCConfig.*;

@Component
public class ClientEventPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private int correlationId = 0;

    public void sendMessage(Message message) {
        rabbitTemplate.send(RECEIVING_EXCHANGE, "", message, new CorrelationData(String.valueOf(correlationId)));
    }
}