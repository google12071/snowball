package org.developer.learn.java.java8;

import lombok.extern.slf4j.Slf4j;
import org.developer.learn.java.concurrent.util.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/***
 * ******************CompletableFuture特性*****************************
 * 1、CompletableFuture可通过编程的方式显示的设置计算结果和任务状态
 * 2、Completable内部默认使用ForkJoinPool.commonPool()线程池执行任务，使用者也可以自定义执行线程池
 * 3、Completable可作为一个CompletionStage(计算阶段)，当起计算完成时，可触发函数或是行为
 * 4、当多个线程试图调用同一个CompletableFuture对象的complete、cancel方法时，仅有一个线程可以执行成功
 *
 */
@Slf4j
public class CompletableFutureTest {

    /**
     * 显示完成任务
     */
    @Test
    public void completeTask() {
        CompletableFuture<String> future = new CompletableFuture<>();
        ThreadUtil.buildPool().execute(() -> {
            log.info("Thread:{},running...", Thread.currentThread().getName());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Thread:{},set future result", Thread.currentThread().getName());
            //完成任务
            future.complete("hello world");
        });

        log.info("Thread:{},wait for result", Thread.currentThread().getName());
        String result = null;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        log.info("completeTask,result:{}", result);
    }

    /**
     * 异步执行，无需关心返回结果,可指定线程池
     */
    @Test
    public void runAsync() {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            try {
                log.info("Thread:{},running start", Thread.currentThread().getName());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Thread:{},running end", Thread.currentThread().getName());
        }, ThreadUtil.buildPool());

        log.info("Thread:{},running start", Thread.currentThread().getName());

        try {
            log.info("future result:{}", future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
