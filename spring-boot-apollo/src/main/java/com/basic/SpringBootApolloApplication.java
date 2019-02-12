package com.basic;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@EnableApolloConfig
@SpringBootApplication
public class SpringBootApolloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApolloApplication.class, args);
	}

}

