package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 111
 */
public class MinDepth {

    public int minDepth(TreeNode root) {

        return calcHeight(root, 1);
    }

    private int calcHeight(TreeNode node, int h) {
        if (node == null) {
            return h;
        }

        int leftHeight = node.left == null ? h : calcHeight(node.left, h + 1);
        int rightHeight = node.right == null ? h : calcHeight(node.right, h + 1);
        return Math.min(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        MinDepth minTreeHeight = new MinDepth();
        Assert.assertEquals(3, minTreeHeight.minDepth(TreeNode.buildTree()));
        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(50);
        root.left.left = new TreeNode(30);
        root.right = new TreeNode(101);
        Assert.assertEquals(2, minTreeHeight.minDepth(root));
    }
}
