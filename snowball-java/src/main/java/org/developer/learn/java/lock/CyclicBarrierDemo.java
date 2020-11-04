package org.developer.learn.java.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierDemo
 * @Description:模拟士兵集合
 * @Author lfq
 * @Date 2020/11/4
 **/
@Slf4j
public class CyclicBarrierDemo {

    public static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier barrier;

        public Soldier(String soldier, CyclicBarrier barrier) {
            this.soldier = soldier;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                //等待士兵集合完毕
                log.info("{},前来报道，等待命令", soldier);
                barrier.await();
                //执行工作
                doWork();
                //等待士兵完成工作
                barrier.await();
                log.info("{},完成任务后，原地待命", soldier);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt(3)));
                log.info("{},完成任务", soldier);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class BarrierWorker implements Runnable {
        private boolean flag;
        private int number;

        public BarrierWorker(boolean flag, int number) {
            this.flag = flag;
            this.number = number;
        }

        @Override
        public void run() {
            if (flag) {
                log.info("士兵:{},完成任务", number);
            } else {
                log.info("士兵:{},集合完毕", number);
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int number = 10;
        Thread[] allSoldier = new Thread[number];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(number, new BarrierWorker(flag, number));
        for (int i = 0; i < number; i++) {
            allSoldier[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier));
            allSoldier[i].start();
        }
    }
}
