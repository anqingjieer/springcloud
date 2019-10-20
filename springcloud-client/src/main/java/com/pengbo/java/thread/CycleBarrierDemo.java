package com.pengbo.java.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author 鲁朋博(pengbo.lu @ ucarinc.com)
 * @version 1.0
 * @description:主要用于演示
 *    cyclicBarrier里面有两个重要的参数，partiec标识请求这个数量程序才开始往下进行，后面的线程主要是更高级的，
 *    在5个线程通过之后优先执行这个方法
 * @date 2019/10/15 21:08 创建
 **/
public class CycleBarrierDemo {
    //模拟50次请求
    private static final int threadCount = 100;
    //创建实例
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(5 , new Runnable() {
        @Override
        public void run() {
            System.out.println("线程优先执行");
        }
    });
    @Test
    public void test(){
        //创建一个线程池，里面有两个线程
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    println(threadNum);
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        }
        executorService.shutdown();
    }
    public void println(int i) throws InterruptedException, TimeoutException, BrokenBarrierException {
        System.out.println("threadm+++++++" + i + "-------------read");
        cyclicBarrier.await();
        System.out.println("threadm+++++++" + i + "------------finish");
    }
}
