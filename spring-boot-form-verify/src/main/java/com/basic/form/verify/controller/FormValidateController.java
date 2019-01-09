package com.basic.form.verify.controller;

import com.basic.form.verify.entity.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

/**
 * @Description 表单验证 2.0 WebMvcConfigurerAdapter 修改为 WebMvcConfigurer
 * @Author zcc
 * @Date 19/01/08
 */
@Controller
public class FormValidateController extends WebMvcConfigurerAdapter {

    /**
     * @Description: 添加视图映射
     * @param:  * @param registry
     * @return: void
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/results";
    }
}
