package org.developer.learn.java.concurrent;

import org.developer.learn.java.lock.ConditionThread;
import org.developer.learn.java.lock.IntLock;
import org.developer.learn.java.lock.SemaphoreThread;
import org.developer.learn.java.lock.TimeLock;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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

    @Test
    public void condition() throws InterruptedException {
        ConditionThread conditionThread = new ConditionThread();
        Thread thread = new Thread(conditionThread);
        thread.start();
        Thread.sleep(3000);

        ReentrantLock lock = conditionThread.getLock();
        Condition condition = conditionThread.getCondition();
        lock.lock();
        condition.signal();
        lock.unlock();
    }

    @Test
    public void semaphoreOperate() {
        final SemaphoreThread semaphoreThread = new SemaphoreThread();
        //创建20个线程
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            service.submit(semaphoreThread);
        }
    }
}
