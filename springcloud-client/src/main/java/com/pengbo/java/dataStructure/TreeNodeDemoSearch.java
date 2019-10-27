package com.pengbo.java.dataStructure;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 鲁朋博(pengbo.lu @ ucarinc.com)
 * @version 1.0
 * @description:
 * @date 2019/10/21 22:10 创建
 **/
public class TreeNodeDemoSearch {
    int val;
    TreeNodeDemoSearch left;
    TreeNodeDemoSearch right;
    TreeNodeDemoSearch(int x) { val = x; }

    public void BFSWithQueue(TreeNodeDemoSearch root) {
        Queue<TreeNodeDemoSearch> queue = new LinkedList<>();
        if (root != null)
            queue.add(root);
        while (!queue.isEmpty()) {
            TreeNodeDemoSearch treeNode = queue.poll();
            //在这里处理遍历到的TreeNode节点
            if (treeNode.left != null)
                queue.add(treeNode.left);
            if (treeNode.right != null)
                queue.add(treeNode.right);
        }

    }
}
