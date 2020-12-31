package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 110
 */
public class IsBalanced {

    public boolean isBalanced(TreeNode root) {
        int leftCount = countNode(root.left);
        int rightCount = countNode(root.right);
        return Math.abs(leftCount - rightCount) <= 2;
    }

    private int countNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return countNode(node.left) + countNode(node.right) + 1;
    }

    public static void main(String[] args) {
        IsBalanced isBalanced = new IsBalanced();
        TreeNode root = new TreeNode(100);
        root.left = TreeNode.buildTree();
        Assert.assertFalse(isBalanced.isBalanced(root));
        Assert.assertTrue(isBalanced.isBalanced(TreeNode.buildTree()));
    }
}
