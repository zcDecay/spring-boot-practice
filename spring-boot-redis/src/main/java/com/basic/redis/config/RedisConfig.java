package com.basic.redis.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

/**
 * @Description redis配置类
 * @Author zcc
 * @Date 18/12/04
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    private final RedisConnectionFactory redisConnectionFactory;

    RedisConfig(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置key序列化
        MyStringRedisSerializer serializer = TedisCacheManager.STRING_REDIS_SERIALIZER;
        // 设置key序列化类,否则key会乱码
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);
        // fast_json serializer
        GenericFastJsonRedisSerializer fastJsonSerializer = TedisCacheManager.FAST_JSON_REDIS_SERIALIZER;
        template.setValueSerializer(fastJsonSerializer);
        template.setHashValueSerializer(fastJsonSerializer);
        // 如果 KeySerializer 或者 ValueSerializer 没有配置, 对应的KeySerializer、ValueSerializer才用这个Serializer
        template.setDefaultSerializer(fastJsonSerializer);

        log.info("redis {}", redisConnectionFactory);
        LettuceConnectionFactory factory = (LettuceConnectionFactory) redisConnectionFactory;
        log.info("spring.redis.database: {}", factory.getDatabase());
        log.info("spring.redis.host: {}", factory.getHostName());
        log.info("spring.redis.port: {}", factory.getPort());
        log.info("spring.redis.timeout: {}", factory.getTimeout());
        log.info("spring.redis.password: {}", factory.getPassword());

        // factory
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 自定义key生成策略
     *
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder builder = new StringBuilder(32);
            builder.append(o.getClass().getSimpleName());
            builder.append(".");
            builder.append(method.getName());
            if (objects.length > 0) {
                builder.append("#");
            }
            String sp = "";
            for (Object object : objects) {
                builder.append(sp);
                if (object == null) {
                    builder.append("NULL");
                } else {
                    builder.append(object.toString());
                }
                sp = ".";
            }
            return builder.toString();
        };
    }

    /**
     * 配置RedisCacheManager,使用cache注解管理redis
     *
     * @return
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        // 初始化一个RedisCacheWriter
        RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        // 设置默认过期时间30m
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(0))
                // .disableCachingNullValues()
                // 使用注解时的序列化、反序列化
                .serializeKeysWith(TedisCacheManager.STRING_SERIALIZATION_PAIR)
                .serializeValuesWith(TedisCacheManager.FAST_JSON_SERIALIZATION_PAIR);
        return new TedisCacheManager(cacheWriter, defaultCacheConfig);
    }

}
