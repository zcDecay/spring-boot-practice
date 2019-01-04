package com.basic.springcache.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private String id;

    private String title;

    private String price;

}
