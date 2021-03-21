package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2021/3/21 18:41
 * @Description:
 */
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // dp[i][j]表示到i,j时的最小和
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        MinPathSum minPathSum = new MinPathSum();
        Assert.assertEquals(7, minPathSum.minPathSum(new int[][]{
                {1,3,1},{1,5,1},{4,2,1}
        }));
        Assert.assertEquals(12, minPathSum.minPathSum(new int[][]{
                {1,2,3},{4,5,6}
        }));
    }
}
