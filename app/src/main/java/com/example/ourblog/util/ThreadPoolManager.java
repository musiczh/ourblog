package com.example.ourblog.util;



import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author singsong
 * 线程池单例类
 */
public class ThreadPoolManager {
    /**
     * defaultPool:核心线程数2，最大线程6，队列长度10，超时时间60s,不允许核心线程死亡
     */
    private static ThreadPoolManager mManager = new ThreadPoolManager();
    private ThreadPoolExecutor defaultPool;


    private ThreadPoolManager(){

        defaultPool = new ThreadPoolExecutor(
                2,
                6,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                new ThreadFactory() {
                    private final AtomicInteger mThreadId = new AtomicInteger(0);
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("my thread pool "+ mThreadId.getAndIncrement());
                        return thread;
                    }
                });
    }

    public static ThreadPoolManager getInstance(){
        return mManager;
    }

    /**
     * 添加普通线程
     * @param r 需要执行的任务
     */
    public void addDefaultTask(Runnable r){
        defaultPool.execute(r);
    }

}
