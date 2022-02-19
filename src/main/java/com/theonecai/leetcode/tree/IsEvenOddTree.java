package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1609
 */
public class IsEvenOddTree {
    public boolean isEvenOddTree(TreeNode root) {
        if ((root.val & 1) == 0) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean inc = level % 2 == 0;
            TreeNode pre = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (inc) {
                    if ((node.val & 1) == 0 || (pre != null && node.val <= pre.val)) {
                        return false;
                    }
                } else {
                    if ((node.val & 1) == 1 || (pre != null && node.val >= pre.val)) {
                        return false;
                    }
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                pre = node;
            }
            level++;
        }

        return true;
    }

    public static void main(String[] args) {
        IsEvenOddTree evenOddTree = new IsEvenOddTree();
        Assert.assertFalse(evenOddTree.isEvenOddTree(BinaryTreeUtil.deserialize("[13,34,32,23,25,27,29,44,40,36,34,30,30,28,26,3,7,9,11,15,17,21,25,null,null,27,31,35,null,37,null,30,null,26,null,null,null,24,null,20,16,12,10,null,null,8,null,null,null,null,null,6,null,null,null,null,null,15,19,null,null,null,null,23,null,27,29,33,37,null,null,null,null,null,null,48,null,null,null,46,null,null,null,42,38,34,32,null,null,null,null,19]")));
    }
}
