package com.pengbo.java.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 鲁朋博(pengbo.lu @ ucarinc.com)
 * @version 1.0
 * @description:  就是CountDownLatch本身还是继承AbstractQueuedSynchronizer这个的，实现的原理类似是将线程的数量减去的方式
 * 用法有一下几种：1.初始化一个线程总数new CountDownLatch(n);每次执行一次，线程数量减一，直到线程数为0时
 * 在CountDownLatch上 await() 的线程就会被唤醒。
 *                 2.设置一个共享的CountDownLatch，将其设置为1，强调的是多个线程在某一时刻同时执行。
 *                 类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。
 *  相比
 * @date 2019/10/11 21:43 创建
 **/
public class CountDownLatchDemo {
    // 请求的数量
    private static final int threadCount = 550;
    public static void main(String[] args) throws InterruptedException {
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {// Lambda 表达式的运用
                try {
                    test(threadnum);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();// 表示一个请求已经被完成
                }

            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }
    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }

}
