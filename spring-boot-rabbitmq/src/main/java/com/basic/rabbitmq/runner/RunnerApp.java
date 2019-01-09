package com.basic.rabbitmq.runner;

import com.basic.rabbitmq.config.Receiver;
import com.basic.rabbitmq.config.Sender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/07
 */
@Component
public class RunnerApp implements CommandLineRunner{

    private final RabbitTemplate rabbitTemplate;

    private final Receiver receiver;

    private final ConfigurableApplicationContext context;

    public RunnerApp(Receiver receiver, RabbitTemplate rabbitTemplate,
                  ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Sender.queueName, "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
    }
}
