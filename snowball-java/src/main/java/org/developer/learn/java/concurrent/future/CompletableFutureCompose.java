package org.developer.learn.java.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * CompletableFuture支持多个CompletableFuture的任务组合
 */

@Slf4j
public class CompletableFutureCompose {
    public static CompletableFuture<String> doSomethingOne(String str){
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("currentThread:{}", Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return str;
        });
    }

    public static CompletableFuture<String> doSomethingTwo(String companyId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("currentThread:{}", Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return companyId + ":google";
        });
    }
}
