package org.developer.learn.java.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchThread
 * @Description:
 * @Author lfq
 * @Date 2020/11/4
 **/
@Slf4j
public class CountDownLatchThread implements Runnable {
    public static final CountDownLatch latch = new CountDownLatch(10);

    @Override
    public void run() {
        try {
            log.info("ThreadName:{},is ready", Thread.currentThread().getName());
            Thread.sleep(new Random().nextInt(3) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
            log.info("ThreadName:{},countDown,count:{}", Thread.currentThread().getName(), latch.getCount());
        }
    }
}
