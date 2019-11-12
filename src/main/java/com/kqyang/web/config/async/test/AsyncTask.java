package com.kqyang.web.config.async.test;

import org.springframework.scheduling.annotation.Async;

public class AsyncTask {
    @Async
    public void saveLog() {
        System.out.println(Thread.currentThread().getName());
    }
}
