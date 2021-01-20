package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 622
 */
public class MaxTreeWidth {

    private int depth;
    private int maxWidth;

    private int maxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        this.maxWidth = 1;
        this.depth = 0;
        visit(root,1);

        dfs(root, 0, 0, (int)Math.pow(2, this.depth) - 1);

        return this.maxWidth;
    }

    private void visit(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        this.depth = Math.max(this.depth, depth);
        visit(node.left, depth + 1);
        visit(node.right, depth + 1);
    }

    private void dfs(TreeNode root, int level, int start, int end) {
        if (root == null) {
            return;
        }

        int mid =(start + end) / 2;
        dfs(root.left, level + 1, start, mid);
        this.maxWidth = Math.max(maxWidth, mid + 1);
        dfs(root.right, level + 1, mid + 1, end);
    }

    public static void main(String[] args) {
        MaxTreeWidth treeWidth = new MaxTreeWidth();
        Assert.assertEquals(2,  treeWidth.maxWidth(BinaryTreeUtil.deserialize("[1,2,3,4]")));
    }
}
