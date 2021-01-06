package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 230
 */
public class KthSmallest {

    private int kth;
    private int visitedCount;

    public int kthSmallest(TreeNode root, int k) {
        this.visitedCount = 0;
        dfs(root, k);
        return this.kth;
    }

    /**
     * 中序遍历是有序的，取前k个数
     * @param node
     * @param k
     */
    private void dfs(TreeNode node, int k) {
        if (node == null || this.visitedCount >= k) {
            return;
        }
        dfs(node.left, k);
        this.visitedCount++;
        if (this.visitedCount == k) {
            this.kth = node.val;
            return;
        }
        dfs(node.right, k);
    }

    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
        // 2 3 5 7 9 15 20
        TreeNode root = TreeNode.buildTree();
        Assert.assertEquals(2, kthSmallest.kthSmallest(root, 1));
        Assert.assertEquals(3, kthSmallest.kthSmallest(root, 2));
        Assert.assertEquals(5, kthSmallest.kthSmallest(root, 3));
        Assert.assertEquals(7, kthSmallest.kthSmallest(root, 4));
        Assert.assertEquals(9, kthSmallest.kthSmallest(root, 5));
        Assert.assertEquals(15, kthSmallest.kthSmallest(root, 6));
        Assert.assertEquals(20, kthSmallest.kthSmallest(root, 7));
    }
}
