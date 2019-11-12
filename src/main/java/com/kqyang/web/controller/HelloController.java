package com.kqyang.web.controller;

import com.kqyang.web.config.entity.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 相当于@Controller + @ResponseBody
 */
@RestController
public class HelloController {

    /**
     * 读取配置文件：第一种方法
     */
    @Autowired
    private Environment env;

    /**
     * 读取配置文件：第二种方法
     * 推荐
     */
    @Value("${com.kqyang.name2}")
    private String name;

    /**
     * 读取配置文件：第三种方法
     * 不推荐
     */
    @Autowired
    private MyConfig myConfig;

    /**
     * 相当于@RequestMapping(method = RequestMethod.GET)
     */
    @GetMapping("hello1")
    public String hello1() {
        String name = env.getProperty("com.kqyang.name1");
        return "hello" + name;
    }

    @GetMapping("hello2")
    public String hello2() {
        return "hello" + name;
    }

    @GetMapping("hello3")
    public String hello3() {
        return "hello" + myConfig.getName3();
    }


}
