package org.developer.learn.java.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPoolTask
 * @Description:
 * @Author lfq
 * @Date 2020/11/6
 **/
@Slf4j
public class ThreadPoolTask implements Runnable {
    @Override
    public void run() {
        try {
            log.info(System.currentTimeMillis() + ",ThreadId:" + Thread.currentThread().getId());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service.submit(new ThreadPoolTask());
        }
        service.shutdown();
    }
}
