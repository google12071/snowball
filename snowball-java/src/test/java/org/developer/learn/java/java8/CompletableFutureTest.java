package org.developer.learn.java.java8;

import lombok.extern.slf4j.Slf4j;
import org.developer.learn.java.concurrent.future.CompletableFutureCompose;
import org.developer.learn.java.concurrent.util.ThreadUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

    /**
     * 异步执行任务，并返回任务结果
     */
    @Test
    public void supplyAsync() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("currentThread:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        });

        try {
            log.info("futureResult:{}", future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步任务A->异步任务B
     * A执行完后，在任务触发回调上执行任务B，其中B无法拿到任务A执行的结果
     */
    @Test
    public void thenRun() {
        CompletableFuture<String> aFuture = CompletableFuture.supplyAsync(() -> {
            log.info("currentThread:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aFuture";
        });

        CompletableFuture<Void> bFuture = aFuture.thenRunAsync(() -> {
            log.info("currentThread:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, ThreadUtil.buildPool());
        try {
            log.info("futureResult:{}", bFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步任务A->异步任务B
     * A执行完后，在任务触发回调上执行任务B，其中B可以拿到任务A执行的结果
     */
    @Test
    public void thenAccept() {
        CompletableFuture<String> aFuture = CompletableFuture.supplyAsync(() -> {
            log.info("currentThread:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aFuture";
        });

        //对aFuture结果进行加工
        CompletableFuture<Void> bFuture = aFuture.thenAcceptAsync(s -> {
            log.info("s:{}", s);
        }, ThreadUtil.buildPool());
        try {
            log.info("futureResult:{}", bFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步任务A->异步任务B
     * A执行完后，激活执行异步任务B，其中B可以拿到任务A执行的结果，也可以获得B任务的执行结果
     */
    @Test
    public void thenApply() {
        CompletableFuture<String> aFuture = CompletableFuture.supplyAsync(() -> {
            log.info("currentThread:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aFuture";
        });


        //对任务A结果进行加工
        CompletableFuture<String> bFuture = aFuture.thenApply(s -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + ",bFuture";
        });

        try {
            log.info("aFutureResult:{},bFutureResult:{}", aFuture.get(), bFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步任务执行完毕后回调，不会阻塞调用线程
     */
    @Test
    public void whenComplete() throws InterruptedException {
        CompletableFuture<String> aFuture = CompletableFuture.supplyAsync(() -> {
            log.info("currentThread:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aFuture";
        });

        aFuture.whenComplete((s, throwable) -> {
            //若没有异常，则打印异步执行任务结果
            if (throwable == null) {
                log.info("s:{}", s);
            } else {
                log.error("error", throwable);
            }
        });
        //挂起当前线程，等待异步任务执行完毕
        Thread.currentThread().join(5000);
    }

    /**
     * 连接计算结果
     */
    @Test
    public void compose() {
        CompletableFuture<String> future = CompletableFutureCompose.doSomethingOne("ABC")
                .thenCompose(CompletableFutureCompose::doSomethingTwo);
        try {
            log.info("result:{}", future.get(15, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 组合任务计算结果
     */
    @Test
    public void combine() {
        CompletableFuture<String> future = CompletableFutureCompose.doSomethingOne("ABC")
                .thenCombine(CompletableFutureCompose.doSomethingTwo("123"), (a, b) -> a + "," + b);
        try {
            log.info("result:{}", future.get(15, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 并行执行多个异步任务，其中当所有任务完成时，返回结果
     */
    @Test
    public void allOf() {
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(CompletableFutureCompose.doSomethingOne("1"));
        futureList.add(CompletableFutureCompose.doSomethingOne("2"));
        futureList.add(CompletableFutureCompose.doSomethingOne("3"));
        futureList.add(CompletableFutureCompose.doSomethingOne("4"));

        CompletableFuture<Void> result = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        try {
            log.info("result:{}", result.get(3, TimeUnit.MINUTES));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 并行执行多个异步任务，其中任意一个完成时，返回结果
     */
    @Test
    public void anyOf() {
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(CompletableFutureCompose.doSomethingOne("A"));
        futureList.add(CompletableFutureCompose.doSomethingOne("B"));
        futureList.add(CompletableFutureCompose.doSomethingOne("C"));
        futureList.add(CompletableFutureCompose.doSomethingOne("D"));

        CompletableFuture<Object> result = CompletableFuture.anyOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        try {
            log.info("result:{}", result.get(3, TimeUnit.MINUTES));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步任务执行异常时，可捕获或抛出
     */
    @Test
    public void exception() {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                log.info("currentThread:{}", Thread.currentThread().getName());
                if (true) {
                    throw new RuntimeException("exception test");
                }
                future.complete("ok");
            } catch (Exception e) {
                e.printStackTrace();
                future.completeExceptionally(e);
            }
        }, "threadA").start();
        try {
            log.info("result:{}", future.get(10, TimeUnit.MINUTES));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
