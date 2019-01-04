package com.basic.redis.config;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 设置缓存过期时间
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheExpire {

    /**
     * 过期时间,默认60s
     *
     * @return
     */
    @AliasFor("expire")
    long value() default 60L;

    /**
     * 过期时间,默认60s
     *
     * @return
     */
    @AliasFor("value")
    long expire() default 60L;

}