package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 1289
 */
public class MinFallingPathSumII {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];
        int preMinIdx = -1;
        int preSecMinIdx = -1;
        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
            if (preMinIdx == -1 || dp[0][preMinIdx] > dp[0][i]) {
                preSecMinIdx = preMinIdx;
                preMinIdx = i;
            } else if (preSecMinIdx == -1 || dp[0][preSecMinIdx] > dp[0][i]){
                preSecMinIdx = i;
            }
        }
        for (int i = 1; i < n; i++) {
            int curMinIdx = -1;
            int curSecMinIdx = -1;
            for (int j = 0; j < n; j++) {
                if (j == preMinIdx) {
                    dp[i][j] = dp[i - 1][preSecMinIdx] + grid[i][j];
                } else {
                    dp[i][j] = dp[i - 1][preMinIdx] + grid[i][j];
                }
                if (curMinIdx == -1 || dp[i][curMinIdx] > dp[i][j]) {
                    curSecMinIdx = curMinIdx;
                    curMinIdx = j;
                } else if (curSecMinIdx == -1 || dp[i][curSecMinIdx] > dp[i][j]) {
                    curSecMinIdx = j;
                }
            }
            preMinIdx = curMinIdx;
            preSecMinIdx = curSecMinIdx;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }
        return res;
    }

    public static void main(String[] args) {
        MinFallingPathSumII pathSum = new MinFallingPathSumII();
        Assert.assertEquals(-192, pathSum.minFallingPathSum(new int[][]{
                {-73,61,43,-48,-36},
                {3,30,27,57,10},
                {96,-76,84,59,-15},
                {5,-49,76,31,-7},
                {97,91,61,-46,67}
        }));
        Assert.assertEquals(13, pathSum.minFallingPathSum(new int[][]{
                {1,2,3},{4,5,6},{7,8,9}
        }));
        Assert.assertEquals(-24, pathSum.minFallingPathSum(new int[][]{
                {-19,57},{-40,-5}
        }));
        Assert.assertEquals(-15, pathSum.minFallingPathSum(new int[][]{
                {-15}
        }));
    }
}
