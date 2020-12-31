package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 104
 */
public class MaxDepth {

    private int depth;
    public int maxDepth(TreeNode root) {
        depth = 0;
        visit(root, 1);
        return depth;
    }

    private void visit(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        this.depth = Math.max(this.depth, depth);
        visit(node.left, depth + 1);
        visit(node.right, depth + 1);
    }

    public static void main(String[] args) {
        MaxDepth maxDepth = new MaxDepth();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.right.right = new TreeNode(6);
        Assert.assertEquals(3, maxDepth.maxDepth(root));
    }
}
