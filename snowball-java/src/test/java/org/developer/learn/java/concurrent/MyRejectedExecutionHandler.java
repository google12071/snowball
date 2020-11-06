package org.developer.learn.java.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName MyRejectedExecutionHandler
 * @Description:
 * @Author lfq
 * @Date 2020/11/6
 **/
@Slf4j
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.info("ThreadPool is full reject...");
    }
}
