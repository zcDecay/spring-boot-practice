package com.springboot.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Girl {

    /**
     * @Id : 声明这是唯一标识
     * @GeneratedValue : 声明这个自增
     */
    @Id
    @GeneratedValue
    private Integer id;

    private String cupSize;

    private Integer age;
}
