package com.theonecai.leetcode.stack;

import java.util.Stack;

/**
 * @Author: theonecai
 * @Date: Create in 2020/6/20 19:21
 * @Description:
 */
public class BSTIterator {
    private Stack<Integer> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        if (root == null) {
            return;
        }
        push(root);
    }

    private void push(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.right != null) {
            push(node.right);
        }
        stack.push(node.val);
        if (node.left != null) {
            push(node.left);
        }
    }

    /** @return the next smallest number */
    public int next() {
        return stack.pop();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator it = new BSTIterator(root);
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}

