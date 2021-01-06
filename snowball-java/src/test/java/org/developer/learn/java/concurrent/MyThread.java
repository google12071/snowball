package org.developer.learn.java.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @ClassName RejectThread
 * @Description:自定义线程拒绝策略
 * @Author lfq
 * @Date 2020/11/6
 **/
@Slf4j
public class MyThread implements Runnable {
    @Override
    public void run() {
        try {
            log.info(Thread.currentThread().getName() + ",start");
            Thread.sleep(new Random().nextInt(3));
            log.info(Thread.currentThread().getName() + ",end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
