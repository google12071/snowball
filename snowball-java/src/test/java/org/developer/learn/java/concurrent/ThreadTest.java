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
    public void customThread() throws InterruptedException {
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
    public void threadPool()throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new MyThread());
        }
        Thread.sleep(2000);
    }
}
