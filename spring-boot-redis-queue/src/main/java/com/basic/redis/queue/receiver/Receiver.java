package com.basic.redis.queue.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @Description Redis消息接收者
 * @Author zcc
 * @Date 19/01/04
 */
@Slf4j
public class Receiver {

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 接收消息
     */
    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
        latch.countDown();
    }
}
