package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2020/10/25 17:12
 * @Description:
 */
public class MatrixMaxValue {

    public int maxValue(int[][] grid) {
        /**
         * dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]) + grid[row][col];
         */
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                dp[i][j] = Math.max(i > 0 ? dp[i - 1][j] : -1, j > 0 ? dp[i][j - 1] : -1) + grid[i][j];
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        MatrixMaxValue matrixMaxValue = new MatrixMaxValue();
        Assert.assertEquals(12, matrixMaxValue.maxValue(new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        }));
    }
}
