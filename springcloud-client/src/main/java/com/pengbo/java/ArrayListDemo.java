package com.pengbo.java;






import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author 鲁朋博(pengbo.lu @ ucarinc.com)
 * @version 1.0
 * @description:
 * @date 2019/9/15 14:17 创建
 **/
public class ArrayListDemo {
    /**
     * 其中ArrayList实现了RandomAccess接口，支持随机访问了
     *  1.数组的容量默认是10，如果是一个空数组的话，第一次增加的时候，取得10和初始化容量的中最大的，作为一个扩容标准
     *  2.扩容的时候我们一般采用的的是位移，位移比整除更加的快。将新容量更新为旧容量的1.5倍，
     *  3.conle的复制是浅复制，之复制的其中元素，所在的内存位置没有复制过来，最终采用的还是还是system类里面的
     *      public static native void arraycopy(Object src,  int  srcPos,
     *                                         Object dest, int destPos,
     *                                         int length);
     *   4.toArray()方法是重新构建了一个数组返回的是一个object
     *   5.toArray(T[] a)返回的是属于自己的对象
     *   6.扩容机制的深度了解：移位运算符就是在二进制的程度上对数字进行位移。按照平移的方向和填充数字分为三种
     *   <<(左移)  、>>(带符号右移)   >>>（无符号右移） 对于大数据的2进制运算,
     *   位移运算符比那些普通运算符的运算要快很多,因为程序仅仅移动一下而已,不去计算,这样提高了效率,节省了资源 　
     *   比如这里：int newCapacity = oldCapacity + (oldCapacity >> 1); 右移一位相当于除2，
     *   右移n位相当于除以 2 的 n 次方。这里 oldCapacity 明显右移了1位所以相当于oldCapacity /2。
     *   todo
     * @param
     */
    @Test
    public  void main() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        System.out.printf("Before add:arrayList.size() = %d\n",arrayList.size());

        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(5);
        arrayList.add(7);
        arrayList.add(9);
        System.out.printf("After add:arrayList.size() = %d\n",arrayList.size());

        System.out.println("Printing elements of arrayList");
        // 三种遍历方式打印元素
        // 第一种：通过迭代器遍历
        System.out.print("通过迭代器遍历:");
        Iterator<Integer> it = arrayList.iterator();
        while(it.hasNext()){
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 第二种：通过索引值遍历
        System.out.print("通过索引值遍历:");
        for(int i = 0; i < arrayList.size(); i++){
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();

        // 第三种：for循环遍历
        System.out.print("for循环遍历:");
        for(Integer number : arrayList){
            System.out.print(number + " ");
        }

        // toArray用法
        // 第一种方式(最常用)
        Integer[] integer = arrayList.toArray(new Integer[0]);

        // 第二种方式(容易理解)
        Integer[] integer1 = new Integer[arrayList.size()];
        arrayList.toArray(integer1);

        // 抛出异常，java不支持向下转型
        //Integer[] integer2 = new Integer[arrayList.size()];
        //integer2 = arrayList.toArray();
        System.out.println();

        // 在指定位置添加元素
        arrayList.add(2,2);
        // 删除指定位置上的元素
        arrayList.remove(2);
        // 删除指定元素
        arrayList.remove((Object)3);
        // 判断arrayList是否包含5
        System.out.println("ArrayList contains 5 is: " + arrayList.contains(5));

        // 清空ArrayList
        arrayList.clear();
        // 判断ArrayList是否为空
        System.out.println("ArrayList is empty: " + arrayList.isEmpty());
    }


    /**
     * 1.左移
     *     ->左移运算符“<<” - 使指定值的所有位都左移规定的次数。
     *     ->左移m<<n 代表把数字m在无溢出的前提下乘以2的n次方。　　
     *
     * 2.右移
     * 　右移运算符“>>” - 使指定值的所有位都右移规定的次数。
     *     右移m>>n 代表把数字m除以2的n次方，原来是正数的还是正数，负数还是负数。
     *
     * 　　注意，如果是单数，也就是二进制末位为1，则结果是将m除以2的n次方的整数商。
     *
     * 3.无符号右移
     *     无符号右移运算符“>>>” - 同右移，但是结果全变正数。
     */
    @Test
    public void test(){
        int a = 16;
        System.out.println(a>>>2);  //无符号右移四位 除以16/2的四次幂
        System.out.println(a<<4);  //代表左移4位 16*2的四次幂
        System.out.println(a>>4);  //代表右移4位 16/2的四次幂
        int b = 15;
        System.out.println(b>>>4);
    }




}
