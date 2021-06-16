package org.developer.learn.java.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

import static org.developer.learn.java.concurrent.util.MethodUtil.methodA;
import static org.developer.learn.java.concurrent.util.MethodUtil.methodB;

@Slf4j
public class FutureTaskTest {
    /***
     * 执行异步任务
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    @Test
    public void futureTaskExample() throws InterruptedException, ExecutionException, TimeoutException {
        long start = System.currentTimeMillis();
        //创建异步任务
        FutureTask<String> taskA = new FutureTask<>(() -> {
            log.info("taskA running");
            return methodA();
        });
        Thread threadA = new Thread(taskA, "theadA");
        threadA.start();

        //主线程执行methodB()
        String mainResult = methodB();

        //在超时时间内阻塞等待执行结果返回
        String taskAResult = taskA.get(10, TimeUnit.SECONDS);

        log.info("mainResult:{},taskAResult:{},cost:{}", mainResult, taskAResult, (System.currentTimeMillis() - start));
    }

    /**
     * 线程池启动异步任务
     */
    @Test
    public void futureTaskInThreadPool() throws InterruptedException, ExecutionException, TimeoutException {
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(availableProcessors, 2 * availableProcessors,
                1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(5), new ThreadPoolExecutor.CallerRunsPolicy());

        long start = System.currentTimeMillis();

        //线程池中执行任务A
        FutureTask<String> taskA = new FutureTask<>(() -> {
            log.info("taskA running,availableProcessors:{}", availableProcessors);
            return methodA();
        });
        threadPool.execute(taskA);


        //主线程执行methodB()
        String mainResult = methodB();

        //在超时时间内阻塞等待执行结果返回
        String taskAResult = taskA.get(10, TimeUnit.SECONDS);

        log.info("mainResult:{},taskAResult:{},cost:{}", mainResult, taskAResult, (System.currentTimeMillis() - start));
    }
}
