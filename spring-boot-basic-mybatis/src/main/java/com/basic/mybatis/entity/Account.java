package com.basic.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private int id ;

    private String name ;

    private double money;

}
