package com.pengbo.java.thread;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author 鲁朋博(17802927165 @ 163.com)
 * @version 1.0
 * @description:
 * @date 2019/9/22 21:21 创建
 **/
public class DeadLockDemo {
    /**
     * @description：main方法有多少线程正在执行
     * @param:
     * @return:
     * @author 鲁朋博(17802927165 @ 163.com)
     * @date  2019/9/22 21:23 创建
     */
    @Test
    public void test(){
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    /**
     * 多个线程同时被阻塞，它们中的一个或者全部都在等待某个资源被释放。由于线程被无限期地阻塞
     * 线程1执行通过之后，等待一秒。线程2开始执行，等待一秒。此时线程一和线程二都想获得对方的资源，这时候是相互等待的过程
     * 造成了死锁的发生。
     * 产生死锁的条件
     * 互斥条件：该资源任意一个时刻只由一个线程占用。
     * 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
     * 不剥夺条件:线程已获得的资源在末使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
     * 循环等待条件:进程之间形成一种头尾相接的循环等待资源关系。
     * 因此程序不可能正常终止
     *      破坏循环等到条件就可以了：按顺序申请资源来进行防御，也就是破坏循环等待条件
     * @param
     */
    @Test
    public  void main() {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程 2").start();
    }
    @Test
    public void test3(){
        System.out.println(8 & 2);
    }
}
