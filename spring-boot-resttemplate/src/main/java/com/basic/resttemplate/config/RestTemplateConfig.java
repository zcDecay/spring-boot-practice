package com.basic.resttemplate.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/07
 */
@Slf4j
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }



    @Bean
    public CommandLineRunner  run(RestTemplate restTemplate) throws Exception {
        return args -> {
            String quote = restTemplate.getForObject(
                    "http://gturnquist-quoters.cfapps.io/api/random", String.class);
            log.info(quote.toString());
        };
    }
}
