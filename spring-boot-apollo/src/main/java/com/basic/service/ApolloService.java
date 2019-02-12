package com.basic.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author zcc
 * @Date 19/02/12
 */
@Component
public class ApolloService {
    @Value("${name}")
    String name;

    @PostConstruct
    public void start(){
        System.out.println(name);
    }
}
