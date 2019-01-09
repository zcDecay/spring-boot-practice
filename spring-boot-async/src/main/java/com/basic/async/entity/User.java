package com.basic.async.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description JsonIgnoreProperties ： 转换实体时忽略json中不存在的字段
 * @Author zcc
 * @Date 19/01/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;

    private String blog;

}
