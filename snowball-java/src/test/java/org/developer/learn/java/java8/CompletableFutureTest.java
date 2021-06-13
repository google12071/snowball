package org.developer.learn.java.java8;

import lombok.extern.slf4j.Slf4j;
import org.developer.learn.java.java8.completableFuture.AskThread;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class CompletableFutureTest {

    @Test
    public void asyncCalculate() throws InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        Thread.sleep(5000);
        future.complete(5);
    }
}
