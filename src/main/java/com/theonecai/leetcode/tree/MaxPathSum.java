package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 *  leetcode 124
 */
public class MaxPathSum {

    private int max;

    public int maxPathSum(TreeNode root) {
        this.max = 0;

        dfs(root);

        return this.max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        this.max = Math.max(this.max, left + right + root.val);

        return Math.max(left, right) + root.val;
    }


    public static void main(String[] args) {
        MaxPathSum maxPathSum = new MaxPathSum();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(-20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Assert.assertEquals(19, maxPathSum.maxPathSum(root));

        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.right = new TreeNode(-10);
        Assert.assertEquals(35, maxPathSum.maxPathSum(root));

        root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Assert.assertEquals(42, maxPathSum.maxPathSum(root));

    }

}
