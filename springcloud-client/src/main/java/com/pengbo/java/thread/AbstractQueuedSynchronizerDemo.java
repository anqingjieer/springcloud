package com.pengbo.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author 鲁朋博(17802927165 @ 163.com)
 * @version 1.0
 * @description:
 * @date 2019/9/25 21:15 创建
 **/
public class AbstractQueuedSynchronizerDemo extends AbstractQueuedSynchronizer {

    /**
     *  其主要的方法为这五种，AbstractQueuedSynchronizer应用了一个模板设计模式呢，只需要用到你关心的方法。其内部实现是一个虚拟的
     *  双向队列，如果请求当前资源的空闲，将请求资源的线程设置为有效线程。并且将资源 设置为锁定状态，如果请求资源被占用，其他线程
     *  进行等待，直到该资源被释放。
     *  代码的实现主要还是 用了一个共享的变量，用于其他线程观察其线程状态
     *  一个线程使用几次  对其进行加几，使用完毕则进行减几，回到最初的状态
     *  //原子地（CAS操作）将同步状态值设置为给定值update如果当前同步状态的值等于expect（期望值）
     * isHeldExclusively()//该线程是否正在独占资源。只有用到condition才需要去实现它。
     * tryAcquire(int)//独占方式。尝试获取资源，成功则返回true，失败则返回false。
     * tryRelease(int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。
     * tryAcquireShared(int)//共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
     * tryReleaseShared(int)//共享方式。尝试释放资源，成功则返回true，失败则返回false。
     */

    // 请求的数量
    private static final int threadCount = 550;

    /**
     * Exclusive（独占）：只有一个线程能执行，如ReentrantLock。又可分为公平锁和非公平锁：  利用其构造函数实现公平和非公平
     * 公平锁：按照线程在队列中的排队顺序，先到者先拿到锁
     * 非公平锁：当线程要获取锁时，无视队列顺序直接去抢锁，谁抢到就是谁的
     * Share（共享）：多个线程可同时执行，如Semaphore/CountDownLatch。Semaphore、
     * CountDownLatCh、 CyclicBarrier、ReadWriteLock 。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        // 一次只能允许执行的线程数量。
        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {// Lambda 表达式的运用
                try {
                    System.out.println("---------------");
                    semaphore.acquire(4);// 获取一个许可，所以可运行线程数量为20/4=5
                    test(threadnum);
                    semaphore.release(4);// 释放一个许可
                    System.out.println("+++++++++++++++");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            });
        }
        threadPool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadnum) throws InterruptedException {
       // Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + threadnum);
      //  Thread.sleep(1000);// 模拟请求的耗时操作
    }
}
