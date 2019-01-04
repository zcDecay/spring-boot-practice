package com.basic.redis.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.ReflectionUtils;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * @Description
 * @Author zcc
 * @Date 18/12/04
 */
@Slf4j
public class TedisCacheManager extends RedisCacheManager implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private Map<String, RedisCacheConfiguration> initialCacheConfiguration = new LinkedHashMap<>();

    /**
     * key serializer
     */
    public static final MyStringRedisSerializer STRING_REDIS_SERIALIZER = new MyStringRedisSerializer();

    /**
     * value serializer
     * 用FastJsonRedisSerializer会有ClassCastException
     */
    public static final GenericFastJsonRedisSerializer FAST_JSON_REDIS_SERIALIZER = new GenericFastJsonRedisSerializer();

    /**
     * key serializer pair
     */
    public static final RedisSerializationContext.SerializationPair<String> STRING_SERIALIZATION_PAIR =
            RedisSerializationContext.SerializationPair.fromSerializer(STRING_REDIS_SERIALIZER);

    /**
     * value serializer pair
     */
    public static final RedisSerializationContext.SerializationPair<Object> FAST_JSON_SERIALIZATION_PAIR =
            RedisSerializationContext.SerializationPair.fromSerializer(FAST_JSON_REDIS_SERIALIZER);

    public TedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        return new RedisCacheWrapper(cache);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            final Class aClass = applicationContext.getType(beanName);
            add(aClass);
        }
        super.afterPropertiesSet();
    }

    @Override
    protected Collection<RedisCache> loadCaches() {
        List<RedisCache> cacheList = new LinkedList<>();
        for (Map.Entry<String, RedisCacheConfiguration> entry : initialCacheConfiguration.entrySet()) {
            cacheList.add(super.createRedisCache(entry.getKey(), entry.getValue()));
        }
        return cacheList;
    }

    private void add(final Class aClass) {
        ReflectionUtils.doWithMethods(aClass, method -> {
            ReflectionUtils.makeAccessible(method);
            CacheExpire cacheExpire = AnnotationUtils.findAnnotation(method, CacheExpire.class);
            if (cacheExpire == null) {
                return;
            }
            Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);
            if (cacheable != null) {
                addExpire(cacheable, cacheExpire, aClass);
                return;
            }
            CachePut cachePut = AnnotationUtils.findAnnotation(method, CachePut.class);
            if (cachePut != null) {
                addExpire(cachePut, cacheExpire, aClass);
                return;
            }
            Caching caching = AnnotationUtils.findAnnotation(method, Caching.class);
            if (caching != null) {
                Cacheable[] cacheables = caching.cacheable();
                if (cacheables.length > 0) {
                    for (Cacheable c : cacheables) {
                        if (cacheExpire != null && c != null) {
                            add(c.cacheNames(), cacheExpire);
                        }
                    }
                }
            } else {
                CacheConfig cacheConfig = AnnotationUtils.findAnnotation(aClass, CacheConfig.class);
                if (cacheConfig != null) {
                    add(cacheConfig.cacheNames(), cacheExpire);
                }
            }
        }, method -> null != AnnotationUtils.findAnnotation(method, CacheExpire.class));
    }

    private <T> void addExpire(T cacheType, CacheExpire cacheExpire, final Class aClass) {
        String[] cacheNames = null;
        if (cacheType instanceof Cacheable) {
            cacheNames = ((Cacheable) cacheType).cacheNames();
        } else if (cacheType instanceof CachePut) {
            cacheNames = ((CachePut) cacheType).cacheNames();
        }
        // 如果方法上有cacheNames就用方法的, 否则找类的
        if (cacheNames == null || cacheNames.length == 0) {
            CacheConfig cacheConfig = AnnotationUtils.findAnnotation(aClass, CacheConfig.class);
            cacheNames = cacheConfig.cacheNames();
        }
        add(cacheNames, cacheExpire);
    }

    private void add(String[] cacheNames, CacheExpire cacheExpire) {
        for (String cacheName : cacheNames) {
            if (StringUtils.isEmpty(cacheName)) {
                continue;
            }
            long expire = cacheExpire.expire();
            if (expire > 0) {
                log.info("cacheName: {}, expire: {}.", cacheName, expire);
            } else {
                log.info("cacheName: {} will never expire.", cacheName);
            }
//			if (expire > 0) {
            RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofSeconds(expire))
                    .disableCachingNullValues()
                    .serializeKeysWith(STRING_SERIALIZATION_PAIR)
                    .serializeValuesWith(FAST_JSON_SERIALIZATION_PAIR);
            initialCacheConfiguration.put(cacheName, configuration);
//			} else {
//				log.warn("{} use default expiration.", cacheName);
//			}
        }
    }

    protected static class RedisCacheWrapper implements Cache {

        private final Cache cache;

        RedisCacheWrapper(Cache cache) {
            this.cache = cache;
        }

        @Override
        public String getName() {
            try {
                return cache.getName();
            } catch (Exception e) {
                log.error("getName ---> errmsg: {}", e.getMessage(), e);
                return null;
            }
        }

        @Override
        public Object getNativeCache() {
            try {
                return cache.getNativeCache();
            } catch (Exception e) {
                log.error("getNativeCache ---> errmsg: {}", e.getMessage(), e);
                return null;
            }
        }

        @Override
        public ValueWrapper get(Object o) {
            try {
                return cache.get(o);
            } catch (Exception e) {
                log.error("get ---> o: {}, errmsg: {}", o, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public <T> T get(Object o, Class<T> aClass) {
            try {
                return cache.get(o, aClass);
            } catch (Exception e) {
                log.error("get ---> o: {}, clazz: {}, errmsg: {}", o, aClass, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public <T> T get(Object o, Callable<T> callable) {
            try {
                return cache.get(o, callable);
            } catch (Exception e) {
                log.error("get ---> o: {}, errmsg: {}", o, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public void put(Object o, Object o1) {
            try {
                cache.put(o, o1);
            } catch (Exception e) {
                log.error("put ---> o: {}, o1: {}, errmsg: {}", o, o1, e.getMessage(), e);
            }
        }

        @Override
        public ValueWrapper putIfAbsent(Object o, Object o1) {
            try {
                return cache.putIfAbsent(o, o1);
            } catch (Exception e) {
                log.error("putIfAbsent ---> o: {}, o1: {}, errmsg: {}", o, o1, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public void evict(Object o) {
            try {
                cache.evict(o);
            } catch (Exception e) {
                log.error("evict ---> o: {}, errmsg: {}", o, e.getMessage(), e);
            }
        }

        @Override
        public void clear() {
            try {
                cache.clear();
            } catch (Exception e) {
                log.error("clear ---> errmsg: {}", e.getMessage(), e);
            }
        }
    }
}

