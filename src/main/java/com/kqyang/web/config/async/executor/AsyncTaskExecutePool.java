package com.kqyang.web.config.async.executor;

import com.kqyang.web.config.async.entity.TaskThreadPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {
    private Logger logger = LoggerFactory.getLogger(AsyncTaskExecutePool.class);

    @Autowired
    private TaskThreadPoolConfig config;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(config.getCorePoolSize());
        executor.setMaxPoolSize(config.getMaxPoolSize());
        executor.setQueueCapacity(config.getQueueCapacity());
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        executor.setThreadNamePrefix(config.getThreadNamePrefix());
        /**
         * 线程池配置的拒绝策略
         *
         * *当我的的线程数量高于线程池的处理速度时，任务会被缓存到本地的队列中。队列也是有大小的，
         * *如果超过了这个大小，就需要有拒绝的策略，否则就会出现内存溢出
         *
         * 常用的两种策略：
         *      *AbortPolicy：直接抛出java.util.concurrent.RejectedExecutionException异常，
         *      那么这个任务就会被丢弃；
         *      *CallerRunsPolicy：主线程直接执行该任务，也就是同步执行，执行完之后尝试添加下一个任
         *      务到线程池中，这样可以有效降低向线程池内添加任务的速度。
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        /**
         * 异步任务中异常处理
         */
        return (Throwable t, Method method, Object... objects) -> {
            logger.error("======================" + t.getMessage() + "======================", t);
            logger.error("exception method:" + method.getName());
        };
    }
}
