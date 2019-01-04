package com.basic.redis.config;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Description 自定义key序列化方式
 * @Author zcc
 * @Date 18/12/04
 */
public class MyStringRedisSerializer implements RedisSerializer<String> {

    private final Charset charset;

    private final String target = "\"";

    private final String replacement = "";

    private final String doubleColon = "::";

    private final String singleColon = ":";

    MyStringRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public MyStringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public byte[] serialize(String o) throws SerializationException {
        String s = JSON.toJSONString(o);
        s = s.replace(target, replacement).replace(doubleColon, singleColon);
        return s.getBytes(charset);
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return bytes == null ? null : new String(bytes, this.charset);
    }

}
