package com.eris.springboottest.threadpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class ThreadPoolTest {

    @Autowired
    private ExecutorConfig executorConfig;

    @Autowired
    private ThreadPoolTaskExecutor queryDisbursementResultExecutor;

    @PostConstruct
    public void init() {
        int count = 3;
        for (int i = 0; i < count; i++) {
            int finalI = i;
            Runnable task = () -> {
                if (finalI % 3 == 0) {
                    throw new RuntimeException("task error1111");
                }
                System.out.println("i=" + finalI + ", task: " + UUID.randomUUID().toString());
            };
            CompletableFuture.runAsync(task, queryDisbursementResultExecutor).exceptionally(e -> {
                System.out.println("task error: " + e.getMessage());
                return null;
            });
        }
    }
}
