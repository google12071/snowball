package org.developer.learn.java.concurrent.threadsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * 并发场景下，ArrayList可能得到不正确的结果
 */
@Slf4j
public class ArrayListThreadSafeCase {
    static ArrayList<Integer> integers = new ArrayList<>(10);

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                integers.add(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(integers.size());
    }
}
