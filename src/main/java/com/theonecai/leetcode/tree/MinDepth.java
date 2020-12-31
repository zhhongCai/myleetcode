package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 111
 */
public class MinDepth {

    public int minDepth(TreeNode root) {

        return calcHeight(root);
    }

    private int calcHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.min(calcHeight(node.left) + 1, calcHeight(node.right) + 1);
    }

    public static void main(String[] args) {
        MinDepth minTreeHeight = new MinDepth();
        Assert.assertEquals(3, minTreeHeight.minDepth(TreeNode.buildTree()));
    }
}
