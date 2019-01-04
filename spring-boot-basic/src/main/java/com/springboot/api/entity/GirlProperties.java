package com.springboot.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description entity
 * @Author zcc
 * @Date 19/01/03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@ConfigurationProperties(prefix="girl")
public class GirlProperties {


    private String name;

    private int age;

    private String content;
}
