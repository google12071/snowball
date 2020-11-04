package org.developer.learn.java.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreThread
 * @Description: 信号量机制：信号量为多线程协作提供了更强大多方法，允许多个线程同时访问
 * 临界区资源
 * @Author lfq
 * @Date 2020/11/4
 **/
@Slf4j
public class SemaphoreThread implements Runnable {
    final Semaphore smp = new Semaphore(5);

    @Override
    public void run() {
        try {
            System.out.println("ThreadId:" + Thread.currentThread().getId() + ",acquire resource");
            smp.acquire();
            Thread.sleep(10000);
            System.out.println("ThreadId:" + Thread.currentThread().getId() + ",wakeup");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            smp.release();
            System.out.println("ThreadId:" + Thread.currentThread().getId() + ",release resource");
        }
    }
}
