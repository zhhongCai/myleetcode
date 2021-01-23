package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 337
 */
public class Rob {

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int m = root.val;
        if (root.left != null) {
            m += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            m +=  rob(root.right.left) + rob(root.right.right);
        }

        return Math.max(m, rob(root.left) + rob(root.right));
    }

    public int rob2(TreeNode root) {
        int[] res = dfs(root);

        return Math.max(res[0], res[1]);
    }

    /**
     * 后序遍历
     * @param node
     * @return
     */
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] dpLeft = dfs(node.left);
        int[] dpRight = dfs(node.right);
        // dp[0] 不偷node，dp[1]偷node
        int[] dp = new int[2];
        // 不偷node，可以偷或不偷left/right(取最大值)
        dp[0] = Math.max(dpLeft[0], dpLeft[1]) +  Math.max(dpRight[0], dpRight[1]);
        dp[1] = dpLeft[0] +  dpRight[0] + node.val;
        return dp;
    }

    public static void main(String[] args) {
        Rob rob = new Rob();
        Assert.assertEquals(7, rob.rob(BinaryTreeUtil.deserialize("[3,2,3,null,3,null,1]")));
        Assert.assertEquals(7, rob.rob2(BinaryTreeUtil.deserialize("[3,2,3,null,3,null,1]")));
        Assert.assertEquals(9, rob.rob(BinaryTreeUtil.deserialize("[3,4,5,1,3,null,1]")));
        Assert.assertEquals(9, rob.rob2(BinaryTreeUtil.deserialize("[3,4,5,1,3,null,1]")));
    }
}
