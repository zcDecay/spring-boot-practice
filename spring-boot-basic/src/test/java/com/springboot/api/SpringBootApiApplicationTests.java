package com.springboot.api;

import com.springboot.api.entity.GirlProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootApiApplicationTests {

	@Autowired
	private GirlProperties girlProperties;
	@Test
	public void contextLoads() {
        System.out.println("***** 获取属性 ****");
        System.out.println(girlProperties.getAge());
        System.out.println(girlProperties.getContent());
        System.out.println(girlProperties.getName());
    }

}

