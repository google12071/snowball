package org.developer.learn.java.concurrent;

import org.developer.learn.java.lock.IntLock;
import org.developer.learn.java.lock.TimeLock;
import org.junit.Test;

/**
 * @ClassName LockTest
 * @Description:
 * @Author lfq
 * @Date 2020/11/3
 **/
public class LockTest {
    @Test
    public void deadLock() throws InterruptedException {
        IntLock lock1 = new IntLock(1);
        IntLock lock2 = new IntLock(2);
        Thread t1 = new Thread(lock1);
        Thread t2 = new Thread(lock2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //中断其中一个线程
        t2.interrupt();
    }

    @Test
    public void timeLock() {
        TimeLock timeLock = new TimeLock(5);
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();
    }
}
