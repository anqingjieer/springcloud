package com.pengbo.java.thread;

/**
 * @author 鲁朋博(17802927165 @ 163.com)
 * @version 1.0
 * @description:ThreadLocal，保证多个线程访问的变量是不一样的。
 * @date 2019/10/17 20:34 创建
 **/
public class ThreadLocalDemo {
    /***
     * 此处为Threadlocal的源码，set操作的原理是：底层有一个ThreadLocalMap
     * 里面的key是线程本身，value是set  value的大小
     */
    //    public void set(T value) {
//        Thread t = Thread.currentThread();
//        ThreadLocal.ThreadLocalMap map = getMap(t);
//        if (map != null)
//            map.set(this, value);
//        else
//            createMap(t, value);
//    }
    static void print(String string) {
        //打印当前缓存中threaLocal的值
        System.out.println(string+"===="+threadLocal.get());
        //清除当前变量的值
        //threadLocal.remove();
    }
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("thread");
                print("threadOne");
                System.out.println("one++++"+threadLocal.get());
            }
        });
        Thread threadTwo=new Thread(new Runnable() {
            @Override
            public void run() {
                //此时这个线程是访问不了线程一的值的，因为是单独存放
                System.out.println("two+++++"+threadLocal.get());
                threadLocal.set("threadTwo");
                print("two");
            }
        });
        threadOne.start();
        threadTwo.start();
    }
}
