package com.theonecai.leetcode.tree;

/**
 * leetcode 938
 * @Author: theonecai
 * @Date: Create in 2021/2/6 22:14
 * @Description:
 */
public class RangeSumBST {
    private int sum;
    public int rangeSumBST(TreeNode root, int low, int high) {
        this.sum = 0;
        dfs(root, low, high);
        return this.sum;
    }

    private void dfs(TreeNode node, int low, int high) {
        if (node == null) {
            return;
        }
        if (low <= node.val && node.val <= high) {
            this.sum += node.val;
        }
        dfs(node.left, low, high);
        dfs(node.right, low, high);
    }
}
