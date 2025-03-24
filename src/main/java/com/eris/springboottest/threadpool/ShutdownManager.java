package com.eris.springboottest.threadpool;

import com.eris.springboottest.aware.SpringBeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShutdownManager implements DisposableBean {

    private static final AtomicBoolean isShuttingDown = new AtomicBoolean(false);

    private final ThreadPoolTaskExecutor myTestExecutor;

    public static boolean isShuttingDown() {
        return isShuttingDown.get();
    }

    @Override
    public void destroy() {
        log.info("Received shutdown signal in {}, shutdown executor now...", this.getClass().getSimpleName());
        isShuttingDown.set(true);

        // add executor to shutdown here
        shutdown(myTestExecutor.getThreadPoolExecutor(), "myTestExecutor");
    }

    private void shutdown(ThreadPoolExecutor executor, String executorName) {
        try {
            executor.shutdown();
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                log.warn("Thread pool {} did not terminate in the specified time.", executorName);
                executor.shutdownNow();
            }
        } catch (Exception e) {
            log.error("Exception occurred while shutdown executor: {}. ", executorName, e);
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
