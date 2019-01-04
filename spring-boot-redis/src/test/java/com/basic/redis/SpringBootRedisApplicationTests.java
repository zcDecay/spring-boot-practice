package com.basic.redis;

import com.basic.redis.dao.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {


    @Test
    public void contextLoads() {
    }


    @Autowired
    RedisDao redisDao;

    @Test
    public void testRedis() {
        redisDao.setKey("name", "forezp");
        redisDao.setKey("age", "11");
        log.info(redisDao.getValue("name"));
        log.info(redisDao.getValue("age"));
    }
}

