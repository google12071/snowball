package org.developer.learn.java.concurrent.basic;

import lombok.SneakyThrows;

import java.util.Date;

/**
 * @ClassName SimpleJavaApp
 * @Description:
 * @Author lfq
 * @Date 2020/8/9
 **/
public class SimpleJavaApp {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        Thread t = new Thread(new SimpleThread());
        t.start();
        while (i < 10) {
            System.out.println(new Date() + "," + Thread.currentThread().getPriority());
            Thread.sleep(1000);
            i++;
            //等待线程执行结束
            t.join();
        }
    }

    public static class SimpleThread implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + ",finish");
        }
    }

}
