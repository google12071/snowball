package org.developer.learn.java.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName IntLock
 * @Description:带线程中断带死锁模拟
 * @Author lfq
 * @Date 2020/11/3
 **/
public class IntLock implements Runnable {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    private int lockNumber;

    /**
     * 给锁加序号，控制加锁顺序，便于模拟死锁现象
     *
     * @param lockNumber
     */
    public IntLock(int lockNumber) {
        this.lockNumber = lockNumber;
    }

    @Override
    public void run() {
        try {
            if (lockNumber == 1) {
                lock1.lockInterruptibly();
                lock2.lockInterruptibly();
            } else if (lockNumber == 2) {
                lock2.lockInterruptibly();
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                System.out.println("线程:" + Thread.currentThread().getId() + "释放锁");
                lock1.unlock();
            } else if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
                System.out.println("线程:" + Thread.currentThread().getId() + "执行退出");
            }
            System.out.println("线程:" + Thread.currentThread().getId() + "执行退出");
        }
    }
}
