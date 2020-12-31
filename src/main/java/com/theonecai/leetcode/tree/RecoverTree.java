package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode 99
 */
public class RecoverTree {

    /**
     * 中序遍历数据是递增的，找到非递增的两个节点x,y，交换即可
     * @param root
     */
    public void recoverTree(TreeNode root) {

        TreeNode x = null;
        TreeNode y = null;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (pre != null) {
                if (pre.val > node.val) {
                    if (x == null) {
                        x = pre;
                        y = node;
                    } else {
                        y = node;
                    }
                }
            }
            pre = node;
            node = node.right;
        }

        swap(x, y);
    }

    private void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    public void recoverTree2(TreeNode root) {
        List<TreeNode> nums = new ArrayList<>();

        visit(root, nums);

        TreeNode x = null;
        TreeNode y = null;
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i).val > nums.get(i + 1).val) {
                if (x == null) {
                    x = nums.get(i);
                    y = nums.get(i + 1);
                } else {
                    y = nums.get(i + 1);
                }
            }
        }
        swap(x, y);
    }

    private void visit(TreeNode node, List<TreeNode> nums) {
        if (node == null) {
            return;
        }
        visit(node.left, nums);
        nums.add(node);
        visit(node.right, nums);
    }

    public static void main(String[] args) {
        //[3,1,4,null,null,2]
        RecoverTree recoverTree = new RecoverTree();
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.right = new TreeNode(4);
        node.right.left = new TreeNode(2);
        recoverTree.recoverTree(node);
        Assert.assertNotNull(node);


        TreeNode root = new TreeNode(7);

        root.left = new TreeNode(3);
        root.right = new TreeNode(15);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        recoverTree.recoverTree2(root);
        Assert.assertNotNull(root);
    }
}
