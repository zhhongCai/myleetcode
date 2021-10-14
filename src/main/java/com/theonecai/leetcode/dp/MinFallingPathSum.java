package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 931
 */
public class MinFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + matrix[i][j];
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + matrix[i][j]);
                    }
                    if (j < n - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1] + matrix[i][j]);
                    }
                }
                if (i == n - 1 && res > dp[i][j]) {
                    res = dp[i][j];
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        MinFallingPathSum pathSum = new MinFallingPathSum();
        Assert.assertEquals(13, pathSum.minFallingPathSum(new int[][]{
                {2,1,3},{6,5,4},{7,8,9}
        }));
        Assert.assertEquals(-59, pathSum.minFallingPathSum(new int[][]{
                {-19,57},{-40,-5}
        }));
        Assert.assertEquals(-15, pathSum.minFallingPathSum(new int[][]{
                {-15}
        }));
    }
}
