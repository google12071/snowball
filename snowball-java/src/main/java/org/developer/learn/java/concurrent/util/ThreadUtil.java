package org.developer.learn.java.concurrent.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程基本工具类
 */
@Slf4j
public class ThreadUtil {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static ThreadPoolExecutor buildPool() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(AVAILABLE_PROCESSORS, 2 * AVAILABLE_PROCESSORS,
                1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(5), new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

    public static ThreadPoolExecutor buildPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, unit, new LinkedBlockingDeque<>(100), new ThreadPoolExecutor.CallerRunsPolicy());

        return pool;
    }

}
