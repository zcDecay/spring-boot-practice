package com.basic.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/04
 */
@Component
public class RedisDao {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        //1分钟过期
        ops.set(key, value, 1, TimeUnit.MINUTES);
    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        return ops.get(key);
    }
}
