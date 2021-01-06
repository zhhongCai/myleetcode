package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 110
 */
public class IsBalanced {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return countLevelNode(root) >= 0;
    }

    private int countLevelNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = countLevelNode(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = countLevelNode(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        IsBalanced isBalanced = new IsBalanced();
        TreeNode root = new TreeNode(100);
        root.right = new TreeNode(101);
        root.right.right = new TreeNode(102);
        Assert.assertFalse(isBalanced.isBalanced(root));

        root = new TreeNode(100);
        root.left = TreeNode.buildTree();
        Assert.assertFalse(isBalanced.isBalanced(root));
        Assert.assertTrue(isBalanced.isBalanced(TreeNode.buildTree()));

        root = new TreeNode(100);
        root.left = new TreeNode(60);
        root.right = new TreeNode(110);
        root.left.left = new TreeNode(50);
        root.left.left.left = new TreeNode(40);
        Assert.assertFalse(isBalanced.isBalanced(root));

        root = new TreeNode(1);
        Assert.assertTrue(isBalanced.isBalanced(root));
    }
}
