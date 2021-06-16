package org.developer.learn.java.java8.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture异步编程应用
 */
@Slf4j
public class AskThread implements Runnable {

    private CompletableFuture<Integer> res;

    public AskThread(CompletableFuture<Integer> res) {
        this.res = res;
    }

    @Override
    public void run() {
        int myRes = 0;
        try {
            myRes = res.get() * res.get();
        } catch (Exception e) {
            log.error("getResult error", e);
        }
        log.info("myRes:{}", myRes);
    }
}
