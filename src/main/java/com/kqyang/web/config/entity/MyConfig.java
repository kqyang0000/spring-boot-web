package com.kqyang.web.config.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件：第三种方法
 * 不推荐
 */
@ConfigurationProperties(prefix = "com.kqyang")
@Component
public class MyConfig {
    private String name3;

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }
}
