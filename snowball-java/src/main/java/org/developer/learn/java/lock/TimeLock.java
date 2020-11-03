package org.developer.learn.java.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TimeLock
 * @Description: 带有超时时间的的争抢锁
 * @Author lfq
 * @Date 2020/11/3
 **/
public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    /**
     * 争抢锁超过指定时间则直接返回
     */
    private int timeout;

    public TimeLock(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(timeout, TimeUnit.SECONDS)) {
                System.out.println("线程:" + Thread.currentThread().getId() + ",成功争抢到锁");
                Thread.sleep(10000);
            } else {
                System.out.println("线程:" + Thread.currentThread().getId() + ",争抢锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println("线程:" + Thread.currentThread().getId() + ",释放锁");
                lock.unlock();
            }
        }
    }
}
