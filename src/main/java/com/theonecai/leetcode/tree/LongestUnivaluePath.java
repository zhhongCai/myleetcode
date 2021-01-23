package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 687
 * @Author: theonecai
 * @Date: Create in 2021/1/23 20:03
 * @Description:
 */
public class LongestUnivaluePath {

    private int max;

    public int longestUnivaluePath(TreeNode root) {
        this.max = 0;
        int[] count = dfs(root);


        this.max = Math.max(this.max - 1, Math.max(count[0], count[1]) - 1);
        return this.max == -1 ? 0 : this.max;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] dpLeft = dfs(node.left);
        int[] dpRight = dfs(node.right);
        // dp[0]表示不含node的路径上的节点数,dp[1]含nde的路径上的节点数
        int[] dp = new int[2];
        dp[1] = 1;
        if (node.left != null) {
            if (node.val == node.left.val) {
                dp[0] = dpLeft[0];
                dp[1] += dpLeft[1];
            } else {
                dp[0] = Math.max(dpLeft[0], dpLeft[1]);
            }
        }
        if (node.right != null) {
            if (node.val == node.right.val) {
                dp[0] = Math.max(dp[0], dpRight[0]);
                this.max = Math.max(this.max, dp[1] + dpRight[1]);
                dp[1] = Math.max(dp[1], dpRight[1] + 1);
            } else {
                dp[0] = Math.max(dp[0], Math.max(dpRight[0], dpRight[1]));
            }
        }

        return dp;
    }

    public static void main(String[] args) {
        LongestUnivaluePath longestUnivaluePath = new LongestUnivaluePath();
        Assert.assertEquals(0, longestUnivaluePath.longestUnivaluePath(
                BinaryTreeUtil.deserialize("[]")
        ));
        Assert.assertEquals(0, longestUnivaluePath.longestUnivaluePath(
                BinaryTreeUtil.deserialize("[1,2,3,4,5,6]")
        ));
        Assert.assertEquals(4, longestUnivaluePath.longestUnivaluePath(
                BinaryTreeUtil.deserialize("[1,1,1,1,1,1]")
        ));
        Assert.assertEquals(2, longestUnivaluePath.longestUnivaluePath(
                BinaryTreeUtil.deserialize("[5,4,5,1,1,5]")
        ));
        Assert.assertEquals(2, longestUnivaluePath.longestUnivaluePath(
                BinaryTreeUtil.deserialize("[5,4,5,4,4,5]")
        ));
    }

}
