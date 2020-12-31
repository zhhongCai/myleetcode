package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 98
 * @Author: theonecai
 * @Date: Create in 2020/12/29 19:33
 * @Description:
 */
public class IsValidBST {

    private boolean isValid;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        isValid = true;

        checkBST(root.left, Long.MIN_VALUE, root.val);
        checkBST(root.right, root.val, Long.MAX_VALUE);

        return isValid;
    }

    private void checkBST(TreeNode node, long min, long max) {
        if (node == null) {
            return;
        }
        if (!isValid) {
            return;
        }
        if (node.val <= min || node.val >= max) {
            isValid = false;
            return;
        }

        checkBST(node.left, min, node.val);
        checkBST(node.right, node.val, max);
    }

    public static void main(String[] args) {
        IsValidBST isValidBST = new IsValidBST();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(7);

        Assert.assertFalse(isValidBST.isValidBST(root));
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
