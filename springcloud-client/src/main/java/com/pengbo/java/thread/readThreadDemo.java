package com.pengbo.java.thread;

import org.junit.jupiter.api.Test;

/**
 * @author 鲁朋博(pengbo.lu @ ucarinc.com)
 * @version 1.0
 * @description:验证多线程条件下的重排
 * @date 2019/9/26 21:55 创建
 **/
public class readThreadDemo {


    @Test
    public void test(){
        new Thread(() -> {
            
        },"线程1").start();
    }
}
