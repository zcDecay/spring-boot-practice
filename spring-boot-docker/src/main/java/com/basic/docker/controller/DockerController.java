package com.basic.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description docker
 * @Author zcc
 * @Date 19/01/08
 */
@RestController
public class DockerController {

    @RequestMapping("/")
    public String home() {
        return "Hello Docker World";
    }

}
