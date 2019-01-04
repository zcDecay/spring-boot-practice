package com.springboot.api.controller;

import com.springboot.api.dao.GirlDao;
import com.springboot.api.entity.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description springboot basic
 *   @RestController : 等同于同时加上了@Controller和@ResponseBody访问/hello或者/hi任何一个地址，都会返回一样的结果
 * @Author zcc
 * @Date 19/01/03
 */
@RestController
public class HelloController {


    @Autowired
    private GirlDao girldao;

    /**
     * 查询所有女生列表
     * @return
     */
    @RequestMapping(value = "/girls",method = RequestMethod.GET)
    public List<Girl> getGirlList(){
        return girldao.findAll();
    }

    /**
     * 添加一个女生
     * @param cupSize
     * @param age
     * @return
     */
    @RequestMapping(value = "/girls",method = RequestMethod.POST)
    public Girl addGirl(@RequestParam("cupSize") String cupSize,
                        @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girldao.save(girl);
    }

}
