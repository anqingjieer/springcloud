package com.pengbo.java.dataStructure;

/**
 * @author 鲁朋博(17802927165 @ 163.com)
 * @version 1.0
 * @description:红黑树的特征：
 *      1.根节点必须是黑色
 *      2.红色节点的下一个节点必须是黑色（反之不一定）
 *      3.叶节点的最后一定是（null/）
 *      4.不会出现两个相连的红色节点
 *      5.最长的节点不可能是最短节点的2倍，这个就是最接近的平衡二叉树了
 *      对树进行遍历：广度遍历（根据树的高度从上往下依次进行遍历,每次遍历玩一层，做标记，然后遍历下一层），
 *                     深度遍历（从左往右，进行遍历）
 * @date 2019/10/21 21:34 创建
 **/
public class RBtreeDemo<T extends Comparable<T>> {
    public static boolean RED = true;
    public static boolean BLACK = false;
    public RBNode<T> mRoot = null;    // 根结点
    /*************对红黑树节点y进行右旋操作 ******************/
    /*
     * 右旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    public void rightRotate(RBNode<T> y) {
        if (y == null) return;
        //1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
        RBNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        //2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
        x.parent = y.parent;
        if (y.parent == null) {
            this.mRoot = x;
        } else {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        //3. 将x的右子节点设为y，将y的父节点设为x
        x.right = y;
        y.parent = x;
    }

    /* 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    public void leftRotate(RBNode<T> x) {
        if (x == null) return;
        //1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        y.parent = x.parent;
        if (x.parent == null) {
            //mRoot是RBTree的根节点
            this.mRoot = y;
        } else {
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        //3. 将y的左子节点设为x，将x的父节点设为y
        y.left = x;
        x.parent = y;
    }

    class RBNode<T extends Comparable<T>> {
        //颜色
        boolean color;
        //关键字（键值）
        T key;
        //左子节点
        RBNode<T> left;
        //右子节点
        RBNode<T> right;
        //父节点
        RBNode<T> parent;

        public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "" + key + (this.color == RED ? "RED" : "BLACK");
        }
    }
}
