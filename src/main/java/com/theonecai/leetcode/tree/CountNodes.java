package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 222
 */
public class CountNodes {

    public int countNodes(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }

        return dfs(node.left) + dfs(node.right) + 1;
    }

    public static void main(String[] args) {
        CountNodes countNodes = new CountNodes();
        Assert.assertEquals(7, countNodes.countNodes(TreeNode.buildTree()));
    }
}
