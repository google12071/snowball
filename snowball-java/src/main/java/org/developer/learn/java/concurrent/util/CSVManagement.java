package org.developer.learn.java.concurrent.util;

import java.util.concurrent.*;

/**
 * @ClassName CSVManagement
 * @Description:
 * @Author lfq
 * @Date 2021/1/29
 **/
public class CSVManagement {

    /**
     * 创建线程池
     */
    private static ExecutorService executor = new ThreadPoolExecutor(10, 15, 0L, TimeUnit.MICROSECONDS,
            new LinkedBlockingQueue<>(), r -> {
        Thread t = new Thread(r);
        return t;
    });


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            executor.submit(new CSVWorker(i));
        }
        executor.shutdown();
        while (true) {
            if (executor.isTerminated()) {
                System.out.println("结束了！");
                break;
            }
            Thread.sleep(2000);
        }
        System.out.println("共计num:" + CSVWorker.getNum());
    }
}
