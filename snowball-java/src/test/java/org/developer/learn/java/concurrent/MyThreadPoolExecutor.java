package org.developer.learn.java.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName MyThreadPoolExecutor
 * @Description:自定义线程池
 * @Author lfq
 * @Date 2020/11/6
 **/
@Slf4j
public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    /**
     * 记录各个线程执行时间
     */
    private ThreadLocal<Map<String, Long>> executeCost = new ThreadLocal<>();

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
                                RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        String threadName = Thread.currentThread().getName();
        Map<String, Long> costMap = executeCost.get();
        if (costMap == null) {
            costMap = new HashMap<>();
        }
        if (costMap.get(threadName) == null) {
            costMap.put(threadName, System.currentTimeMillis());
            log.info(threadName + "is ready beforeExecute");
            executeCost.set(costMap);
        }
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        String threadName = Thread.currentThread().getName();
        Map<String, Long> costMap = executeCost.get();
        long start = costMap.get(threadName);
        long cost = System.currentTimeMillis() - start;
        costMap.put(threadName, cost);
        executeCost.set(costMap);
        log.info(threadName + "is ready afterExecute,cost:{}", cost);
    }

    /**
     * 获取线程池参数信息
     */
    public void getThreadPoolInfo() {
        log.info("corePoolSize:{},poolSize:{},activeCount:{},maximumPoolSize:{},completedTasks:{}", getCorePoolSize(), getPoolSize(), getActiveCount(), getMaximumPoolSize(), getCompletedTaskCount());
    }
}
