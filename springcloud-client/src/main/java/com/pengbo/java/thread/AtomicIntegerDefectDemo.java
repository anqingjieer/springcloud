package com.pengbo.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 鲁朋博(pengbo.lu @ ucarinc.com)
 * @version 1.0
 * @description:演示原子类，多个线程同时操作一个元素，一个操作一旦开始，其他的操作旧不允许进行
 *          演示CAS中的ABA问题操作了，CAS的操作，用期望的值和内存的值进行比较，内存的值一定是属于最新，因为这个是在内存里面了，
 *          用volatile进行修饰的。
 * @date 2019/9/25 20:45 创建
 **/
public class AtomicIntegerDefectDemo {
    /**
     * public final int get() //获取当前的值
     * public final int getAndSet(int newValue)//获取当前的值，并设置新的值
     * public final int getAndIncrement()//获取当前的值，并自增
     * public final int getAndDecrement() //获取当前的值，并自减
     * public final int getAndAdd(int delta) //获取当前的值，并加上预期的值
     * boolean compareAndSet(int expect, int update) //如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update）
     * public final void lazySet(int newValue)//最终设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
     * @param args
     */
    public static void main(String[] args) {
        defectOfABA();
    }

    static void defectOfABA() {
        final AtomicInteger atomicInteger = new AtomicInteger(1);

        Thread coreThread = new Thread(
                () -> {
                    final int currentValue = atomicInteger.get();
                    System.out.println(Thread.currentThread().getName() + " ------ currentValue=" + currentValue);

                    // 这段目的：模拟处理其他业务花费的时间  数据结构
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    boolean casResult = atomicInteger.compareAndSet(1, 2);
                    System.out.println(Thread.currentThread().getName()
                            + " ------ currentValue=" + currentValue
                            + ", finalValue=" + atomicInteger.get()
                            + ", compareAndSet Result=" + casResult);
                }
        );
        coreThread.start();

        // 这段目的：为了让 coreThread 线程先跑起来
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread amateurThread = new Thread(
                () -> {
                    int currentValue = atomicInteger.get();
                    boolean casResult = atomicInteger.compareAndSet(1, 2);
                    System.out.println(Thread.currentThread().getName()
                            + " ------ currentValue=" + currentValue
                            + ", finalValue=" + atomicInteger.get()
                            + ", compareAndSet Result=" + casResult);

                    currentValue = atomicInteger.get();
                    casResult = atomicInteger.compareAndSet(2, 1);
                    System.out.println(Thread.currentThread().getName()
                            + " ------ currentValue=" + currentValue
                            + ", finalValue=" + atomicInteger.get()
                            + ", compareAndSet Result=" + casResult);
                }
        );
        amateurThread.start();
        System.out.println(atomicInteger.get());
    }

}
