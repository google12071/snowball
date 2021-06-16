package org.developer.learn.java.java8;

import lombok.extern.slf4j.Slf4j;
import org.developer.learn.java.java8.completableFuture.AskThread;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class CompletableFutureTest {

    @Test
    public void asyncCalculate() throws InterruptedException, ExecutionException, TimeoutException {
//        CompletableFuture<Integer> future = new CompletableFuture<>();
//        new Thread(new AskThread(future)).start();
//        Thread.sleep(5000);
//        future.complete(60);

        final CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
                return 30 / 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        }).exceptionally(e -> {
            log.error("calculate failure error", e);
            return 0;
        });

        Integer res = future2.get(1, TimeUnit.SECONDS);
        System.out.println(res);
    }

    /**
     * 流式调用
     */
    @Test
    public void streamInvoke() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(String::valueOf).thenApply(String::valueOf).thenAccept(System.out::println);
        future.get(1, TimeUnit.SECONDS);
    }

}
