package com.raven.rpc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RPCConfig {
    public static final String RECEIVING_EXCHANGE = "rabbit-receiving-exchange";
    public static final String REPLY_EXCHANGE = "rabbit-reply-exchage";
    public static final String RECEIVING_QUEUE = "rabbit-receiving-queue";
    public static final String REPLY_QUEUE = "rabbit-reply-queue";

    @Bean
    public Exchange receivingExchange() {
        return new FanoutExchange(RECEIVING_EXCHANGE);
    }

    @Bean
    public Exchange replyExchange() {
        return new FanoutExchange(REPLY_EXCHANGE);
    }

    @Bean
    public Queue receivingQueue() {
        return new Queue(RECEIVING_QUEUE);
    }

    @Bean
    public Queue replyQueue() {
        return new Queue(REPLY_QUEUE);
    }

    @Bean
    public Binding bindReply(Exchange replyExchange, Queue replyQueue) {
        return BindingBuilder.bind(replyQueue).to(replyExchange).with("*").noargs();
    }

    @Bean
    public Binding bindReceiving(Exchange receivingExchange, Queue receivingQueue) {
        return BindingBuilder.bind(receivingQueue).to(receivingExchange).with("*").noargs();
    }
}
