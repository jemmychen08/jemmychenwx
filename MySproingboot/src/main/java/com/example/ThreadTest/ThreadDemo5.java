package com.example.ThreadTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Auther: jemmy
 * @Date: 2019/7/12 09:57
 * @Description:
 */
public class ThreadDemo5 {
    public static void main(String[] args) {
        /**
         * corePoolSize： 线程池维护线程的最少数量
         * maximumPoolSize：线程池维护线程的最大数量
         *
         * keepAliveTime： 线程池维护线程所允许的空闲时间
         *
         *
         * unit： 线程池维护线程所允许的空闲时间的单位
         *unit可选的参数为java.util.concurrent.TimeUnit中的几个静态属性：
         * NANOSECONDS、MICROSECONDS、MILLISECONDS、SECONDS
         *
         * workQueue： 线程池所使用的缓冲队列
         * workQueue我常用的是：java.util.concurrent.ArrayBlockingQueue
         *
         * handler： 线程池对拒绝任务的处理策略
         * handler有四个选择：
         * ThreadPoolExecutor.AbortPolicy()
         * 抛出java.util.concurrent.RejectedExecutionException异常
         * ThreadPoolExecutor.CallerRunsPolicy()
         * 重试添加当前的任务，他会自动重复调用execute()方法
         * ThreadPoolExecutor.DiscardOldestPolicy()
         * 抛弃旧的任务
         * ThreadPoolExecutor.DiscardPolicy()
         * 抛弃当前的任务
         */
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("ThreadDemo5-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0L, TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0;i<Integer.MAX_VALUE;i++){
            threadPoolExecutor.execute(() -> System.out.println("线程1开始执行！！"+Thread.currentThread().getName()));
        }



        ExecutorService threadPoolExecutor1 = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024));
        threadPoolExecutor1.execute(() -> System.out.println("线程2开始执行！！"));
    }
}
