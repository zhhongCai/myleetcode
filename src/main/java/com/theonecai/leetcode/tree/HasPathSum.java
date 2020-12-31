package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 112
 */
public class HasPathSum {
    public boolean hasPathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == target;
        }
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    public static void main(String[] args) {
        HasPathSum pathSum = new HasPathSum();
        Assert.assertTrue(pathSum.hasPathSum(TreeNode.buildTree(), 12));
        Assert.assertTrue(pathSum.hasPathSum(TreeNode.buildTree(), 15));
        Assert.assertTrue(pathSum.hasPathSum(TreeNode.buildTree(), 31));
        Assert.assertTrue(pathSum.hasPathSum(TreeNode.buildTree(), 42));
        Assert.assertFalse(pathSum.hasPathSum(TreeNode.buildTree(), 14));
    }
}
