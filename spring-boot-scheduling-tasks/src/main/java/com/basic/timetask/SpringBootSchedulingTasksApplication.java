package com.basic.timetask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 开启调度任务
 */
@EnableScheduling
@SpringBootApplication
public class SpringBootSchedulingTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSchedulingTasksApplication.class, args);
	}

}

