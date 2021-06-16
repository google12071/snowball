package org.developer.learn.java.concurrent.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodUtil {
    public static String methodA() {
        try {
            log.info("methodA() invoke start,threadName:{}", Thread.currentThread().getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("methodA() error", e);
        }
        log.info("methodA() invoke end,threadName:{}", Thread.currentThread().getName());
        return "A";
    }

    public static String methodB() {
        try {
            log.info("methodB() invoke start,threadName:{}", Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("methodB() error", e);
        }
        log.info("methodB() invoke end,threadName:{}", Thread.currentThread().getName());
        return "B";
    }
}
