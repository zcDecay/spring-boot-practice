package com.basic.form.verify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 后台进行表单验证 不适用boot版本2.1.1，没有hibernate-validator包，若使用需要指定版本号
 * @param:  * @param null
 * @return:
 */
@SpringBootApplication
public class SpringBootFormVerifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFormVerifyApplication.class, args);
	}

}

