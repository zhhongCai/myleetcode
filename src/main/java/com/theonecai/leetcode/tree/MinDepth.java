package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 111
 */
public class MinDepth {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return calcHeight(root, 1);
    }

    private int calcHeight(TreeNode node, int h) {
        if (node == null) {
            return h;
        }
        if (node.left == null && node.right == null) {
            return h;
        }

        if (node.left == null) {
            return calcHeight(node.right, h + 1);
        } else {
            int leftHeight = calcHeight(node.left, h + 1);
            if (node.right == null) {
                return leftHeight;
            }
            int rightHeight = calcHeight(node.right, h + 1);
            return Math.min(leftHeight, rightHeight);
        }
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
