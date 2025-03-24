package com.eris.springboottest.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
public class ExecutorConfig {

    private static final Object LOCK = new Object();

    private int myTestThreadSize = 5;

    private int myTestQueueSize = 10;


    @Bean
    public ThreadPoolTaskExecutor myTestExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(myTestThreadSize);
        executor.setMaxPoolSize(myTestThreadSize);
        executor.setQueueCapacity(myTestQueueSize);
        executor.setThreadNamePrefix("myTestExecutor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }



}
