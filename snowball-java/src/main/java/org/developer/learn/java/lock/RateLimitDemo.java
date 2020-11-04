package org.developer.learn.java.lock;


import com.google.common.util.concurrent.RateLimiter;

/**
 * @ClassName RateLimiteDemo
 * @Description:
 * @Author lfq
 * @Date 2020/11/4
 **/
public class RateLimitDemo {
    /**
     * 定义每秒处理2个请求
     */
    private static RateLimiter limiter = RateLimiter.create(200);

    public static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            if (limiter.tryAcquire()) {
                System.out.println("当前请求速率正常");
                new Thread(new Task()).start();
            } else {
                System.out.println("当前请求过于繁忙，请稍后重试");
            }
        }
    }
}
