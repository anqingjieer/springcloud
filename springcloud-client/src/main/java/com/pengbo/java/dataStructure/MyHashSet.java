package com.pengbo.java.dataStructure;


/**
 * 数组加链表实现 hashSet
 */
public class MyHashSet {


    static class Node {
        int val;
        Node prev, next;

        Node (int val) {
            this.val = val;
        }
    }
    // 初始化数组的长度
    private int length = 100;
    private Node[] data = new Node[length];

    public void add(int key){

        // 使用简单的取余作为hash算法
        int index = key % length;
        Node curr = data[index];
        if (curr == null) {
            Node node = new Node(key);
            // 初始化Node
            data[index] = node;
            return;
        }
        while(true) {
            // 如果值重复的话，也就是桶中的值相等，直接返回，说明已经有数值了。。。
            if (curr.val == key) {
                return;
            }
            // 如果第一个有值的，看下一个Node,
            if(curr.next == null) {
                Node node = new Node(key);
                node.prev = curr;
                curr.next = node;
                return;
            } else {
                curr = curr.next;
            }
        }
    }


    public void remove(int key) {
        int index = key % length;
        Node curr = data[index];
        for (Node d : data) {
            if (d.val == curr.val) {
                data[index] = null;
            }
        }

    }

    public boolean contains(int key) {
        int index = key % length;
        Node curr = data[index];
        for (Node d : data) {
            if (d.val == curr.val) {
                return true;
            }
        }
        return false;
    }



}
