package com.pengbo.java.thread;

/**
 * @author 鲁朋博(pengbo.lu @ ucarinc.com)
 * @version 1.0
 * @description:synchronized的用法
 *  1.用在实例，进入同步代码之前要要获取当前对象的锁
 *  2.修饰静态方法，也就是给类上加锁，
 *  因为访问静态 synchronized 方法占用的锁是当前类的锁，
 *  而访问非静态 synchronized 方法占用的锁是当前实例对象锁。
 * @date 2019/9/22 21:42 创建
 **/
public class synchronizedDemo {


    //uniqueInstance 采用 volatile 关键字修饰也是很有必要的， uniqueInstance = new Singleton();
    // 这段代码其实是分为三步执行
    /**
     * 为uniqueInstance分配空间
     * 初始化 uniqueInstance
     * 将 uniqueInstance 指向分配的内存地址
     */
    private volatile static synchronizedDemo uniqueInstance;

    /**关键字的用法，我们可以通过编译成功的class文件看出，每一个加上同步语句都有
     */
    private synchronizedDemo() {
    }


    /**
     * 都是通过private构造方法去进行
     * @return
     */
    public static synchronizedDemo getUniqueInstance() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (synchronizedDemo.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new synchronizedDemo();
                }
            }
        }
        return uniqueInstance;
    }
}
