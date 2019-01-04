package com.basic.swagger.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    Long id;

    String name;

    String price;
}
