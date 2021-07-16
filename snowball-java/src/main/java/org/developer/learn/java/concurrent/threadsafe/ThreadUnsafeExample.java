package org.developer.learn.java.concurrent.threadsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadUnsafeExample
 * @Description:非线程安全示例
 * @Author lfq
 * @Date 2021/7/16
 **/
@Slf4j
public class ThreadUnsafeExample {
    /**
     * 线程共享变量
     */
    private int count = 0;

    public void increment() {
        count++;
        log.info("increment,count:{},threadName:{}", count, Thread.currentThread().getName());
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;

        final CountDownLatch latch = new CountDownLatch(1000);
        ThreadUnsafeExample unsafeExample = new ThreadUnsafeExample();
        //创建1000个线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.submit(() -> {
                unsafeExample.increment();
                latch.countDown();
            });
        }
        latch.await();

        if (unsafeExample.getCount() != 1000) {
            log.info("count:{}", unsafeExample.getCount());
        }
        executorService.shutdown();
    }
}
