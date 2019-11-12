package com.kqyang.web;

import com.kqyang.web.config.StartCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootWebApplication {

    public static void main(String[] args) {
        /**
         * 启动参数设置
         */
        new StartCommand(args);
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

}
