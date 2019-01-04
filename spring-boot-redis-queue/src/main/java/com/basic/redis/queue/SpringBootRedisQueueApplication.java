package com.basic.redis.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootApplication
public class SpringBootRedisQueueApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(SpringBootRedisQueueApplication.class, args);

        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        CountDownLatch latch = ctx.getBean(CountDownLatch.class);

        log.info("Sending message...");
        template.convertAndSend("chat", "Hello from Redis!");

        latch.await();

        System.exit(0);
    }

}

