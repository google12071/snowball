package org.developer.learn.java.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionThread
 * @Description: Condition对象常与ReentrantLock重入锁搭配使用，分别通过调用await()和signal方法，
 * 控制线程放弃获取锁等待及唤醒线程重新获取锁
 * @Author lfq
 * @Date 2020/11/4
 **/
public class ConditionThread implements Runnable {
    /**
     * 创建一个重入锁
     */
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * 根据重入锁获取Condition对象
     */
    private static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("线程:" + Thread.currentThread().getId() + ",获取锁");
            condition.await();
            System.out.println("线程:" + Thread.currentThread().getId() + ",继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 返回重入锁
     *
     * @return
     */
    public ReentrantLock getLock() {
        return lock;
    }

    /**
     * 返回Condition对象
     *
     * @return
     */
    public Condition getCondition() {
        return condition;
    }
}
