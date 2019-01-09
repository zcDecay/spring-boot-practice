package com.basic.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/08
 */
@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
public class ServiceConfiguration {
    @Bean
    public Service service(ServiceProperties properties) {
        return new Service(properties.getMessage());
    }

}
