package org.developer.learn.java.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @ClassName ThreadTest
 * @Description:
 * @Author lfq
 * @Date 2020/11/5
 **/
@Slf4j
public class ThreadTest {

    @Test
    public void basic() {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        log.info("cpuNum:{}", cpuNum);
    }

    /**
     * 自定义线程池
     */
    @Test
    public void customThread() {
        ExecutorService executor = new MyThreadPoolExecutor(5, 5, 10L
                , TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10), r -> {
            Thread t = new Thread(r);
            t.setName("myThread-" + t.getId());
            return t;
        }, new MyRejectedExecutionHandler());

        for (int i = 0; i < 20; i++) {
            executor.execute(new MyThread());
        }
    }

    @Test
    public void myThread() throws Exception {
        ExecutorService executor = new ThreadPoolExecutor(5, 5, 0L
                , TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("myThread-" + t.getId());
                log.info("create thread:{}", t.getId());
                return t;
            }
        }, new MyRejectedExecutionHandler());

        for (int i = 0; i < 5; i++) {
            executor.execute(new MyThread());
        }
        Thread.sleep(5000);
    }

    @Test
    public void threadPool() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new MyThread());
        }
        Thread.sleep(2000);
    }


    /**
     * 线程中断并设置中断标识位
     */
    @Test
    public void threadInterrupt() {
        log.info("currentThread:{}", Thread.currentThread().getName());
        Thread t1 = new Thread(() -> {
            while (true) {
                //当前线程被中断，则直接退出
                if (Thread.currentThread().isInterrupted()) {
                    log.info("ThreadInterrupted:{}", Thread.currentThread().getName());
                    break;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.info("Interrupted when sleep,Thread:{}", Thread.currentThread().getName());
                    //当前线程设置中断标记
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }, "thread1");
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.info("Interrupted when sleep,Thread:{}", Thread.currentThread().getName());
        }
        //中断线程1
        t1.interrupt();
    }
}
