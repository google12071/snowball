package org.developer.learn.java.concurrent.basic;

/**
 * @ClassName Sequence
 * @Description:
 * @Author lfq
 * @Date 2020/8/10
 **/
public class Sequence implements Runnable {
    public static int count = 0;

    public int increment() {
        return count++;
    }

    public int getValue() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Sequence());
            threads[i].start();
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ",increment");
        increment();
        System.out.println(Thread.currentThread().getName() + ",value:" + getValue());
    }
}
