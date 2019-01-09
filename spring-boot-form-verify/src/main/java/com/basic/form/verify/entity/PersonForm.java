package com.basic.form.verify.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Description 表单验证实体类
 * @Author zcc
 * @Date 19/01/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonForm {

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Min(18)
    private Integer age;
}
