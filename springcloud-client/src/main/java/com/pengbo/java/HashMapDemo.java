package com.pengbo.java;

import java.util.Collection;


import java.util.Set;

public class HashMapDemo {


    /**
     * 总结一下HashMap的put方法，
     * 1.如果未初始化或者table的长度大于0，进行扩容
     * 2.通过取模运算计算他是在那个桶中，如果桶为空，将其放在桶中，将第一个元素赋给e，这个e只是用来记录数据的
     * 3.比较桶中的第一个元素的hash值相等，就是key相等，将第一个元素赋给e
     * 5.看这个节点是否属于红黑树节点，是，则放入树中
     * 6.到达链表的尾部，尾部插入节点，节点到达阈值，转化为红黑树
     * 7.判断结点的key值与插入的元素key值是否相等，相等直接跳出循环
     * ①如果定位到的数组位置没有元素 就直接插入。
     * ②如果定位到的数组位置有元素就和要插入的key比较，如果key相同就直接覆盖，如果key不相同，
     * 就判断p是否是一个树节点，如果是就调用e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value)
     * 将元素添加进入。如果不是就遍历链表插入(插入的是链表尾部)。
     * jdk1.7
     * ①如果定位到的数组位置没有元素 就直接插入。
     * ②如果定位到的数组位置有元素，遍历以这个元素为头结点的链表，
     *   依次和插入的key比较，如果key相同就直接覆盖，不同就采用头插法插入元素。
     *
     *  hash算法
     *   // 1.key.hashCode取到key的hashcode值，
     *   // 2.h ^ (h >>> 16)  为第二步 高位参与运算
     *   // 3.(n - 1) & hash  取模运算，查看元素是放在哪个桶中
     *  还有最重要的一点就是取hash值
     * 扩容
     *  (resize)就是重新计算容量，向HashMap对象里不停的添加元素，而HashMap对象内部的数组无法装载更多的元素时，对象就需要扩大数组的长度，
     *  以便能装入更多的元素。
     *  当然Java里的数组是无法自动扩容的，方法是使用一个新的数组代替已有的容量小的数组，就像我们用一个小桶装水，
     *  如果想装更多的水，就得换大水桶。
     *  自己理解扩容的步骤
     *  初始化参数大小，包括键值对的数量，node数组的大小
     *  1.判断老bucket数组的长度是否大于0，
     *    是否大于阈值，大于则返回，不需要扩容
     *    没有超过最大值，则进行扩容，通过位运算扩容位原来的两倍
     *  2.如果原来的键值对总数大于0，将老的键值对数量赋值给新的键值对数量，
     *  3.如果没有大于0，则初始化默认大小。容量为默认16，最大的键值对为16*0.75（0.75是默认的加载因子，是时间和空间的的中介值）
     *   开始将老的node赋值到新的node里面，然后进行返回
     *  5.初始化一个Node<k,v>,初始化其table的大小
     *    如果老的node不等空的话，直接返回初始值就行
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        java.util.HashMap<String, String> map = new java.util.HashMap<String, String>();
        // 键不能重复，值可以重复
        map.put("san", "张三");
        map.put("si", "李四");
        map.put("wu", "王五");
        map.put("wang", "老王");
        map.put("wang", "老王2");// 老王被覆盖
        map.put("lao", "老王");
        System.out.println("-------直接输出hashmap:-------");
        System.out.println(map);
        map.get("san");
        /**
         * 遍历HashMap
         */
        // 1.获取Map中的所有键
        System.out.println("-------foreach获取Map中所有的键:------");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.print(key+"  ");
        }
        System.out.println();//换行
        // 2.获取Map中所有值
        System.out.println("-------foreach获取Map中所有的值:------");
        Collection<String> values = map.values();
        for (String value : values) {
            System.out.print(value+"  ");
        }
        System.out.println();//换行
        // 3.得到key的值的同时得到key所对应的值
        System.out.println("-------得到key的值的同时得到key所对应的值:-------");
        Set<String> keys2 = map.keySet();
        for (String key : keys2) {
            System.out.print(key + "：" + map.get(key)+"   ");

        }
        /**
         * 另外一种不常用的遍历方式
         */
        // 当我调用put(key,value)方法的时候，首先会把key和value封装到
        // Entry这个静态内部类对象中，把Entry对象再添加到数组中，所以我们想获取
        // map中的所有键值对，我们只要获取数组中的所有Entry对象，接下来
        // 调用Entry对象中的getKey()和getValue()方法就能获取键值对了
        Set<java.util.Map.Entry<String, String>> entrys = map.entrySet();
        for (java.util.Map.Entry<String, String> entry : entrys) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }

        /**
         * HashMap其他常用方法
         */
        System.out.println("after map.size()："+map.size());
        System.out.println("after map.isEmpty()："+map.isEmpty());
        System.out.println(map.remove("san"));
        System.out.println("after map.remove()："+map);
        System.out.println("after map.get(si)："+map.get("si"));
        System.out.println("after map.containsKey(si)："+map.containsKey("si"));
        System.out.println("after containsValue(李四)："+map.containsValue("李四"));
        System.out.println(map.replace("si", "李四2"));
        System.out.println("after map.replace(si, 李四2):"+map);
    }

}