package com.basic.springcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * EnableCaching : 开启缓存技术
 */
@EnableCaching
@SpringBootApplication
public class SpringBootSpringcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSpringcacheApplication.class, args);
	}

}

