package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 226
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = invertTree(left);
        root.left = invertTree(right);

        return root;
    }

    public static void main(String[] args) {
        InvertTree invertTree = new InvertTree();
        TreeNode root = invertTree.invertTree(TreeNode.buildTree());
        Assert.assertNotNull(root);
    }
}
