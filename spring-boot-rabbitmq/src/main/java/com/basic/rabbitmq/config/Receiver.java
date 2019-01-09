package com.basic.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @Description 消息接收者
 * @Author zcc
 * @Date 19/01/07
 */
@Slf4j
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        log.info("Received --->{}", message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
