package com.basic.restdocs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/04
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> greeting() {

        return Collections.singletonMap("message", "Hello World");
    }

}
