package com.basic.service;

import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/08
 */
@Component
public class Service {

    private final String message;

    public Service(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }

}
