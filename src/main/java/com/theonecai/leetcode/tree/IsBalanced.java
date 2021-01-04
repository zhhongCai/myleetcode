package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 110
 */
public class IsBalanced {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return false;
        }
        int leftCount = countLevelNode(root.left, 1);
        int rightCount = countLevelNode(root.right, 1);
        return leftCount == rightCount;
    }

    private int countLevelNode(TreeNode node, int level) {
        if (node == null) {
            return level;
        }
        int leftHeight = node.left == null ? level : countLevelNode(node.left, level + 1);
        int rightHeight = node.right == null ? level :  countLevelNode(node.right, level + 1);
        return Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        IsBalanced isBalanced = new IsBalanced();
        TreeNode root = new TreeNode(100);
        root.left = TreeNode.buildTree();
        Assert.assertFalse(isBalanced.isBalanced(root));
        Assert.assertTrue(isBalanced.isBalanced(TreeNode.buildTree()));

        root = new TreeNode(100);
        root.left = new TreeNode(60);
        root.right = new TreeNode(110);
        root.left.left = new TreeNode(50);
        root.left.left.left = new TreeNode(40);
        Assert.assertFalse(isBalanced.isBalanced(root));
    }
}
