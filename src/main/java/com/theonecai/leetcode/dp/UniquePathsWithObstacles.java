package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 63
 * @Author: theonecai
 * @Date: Create in 2021/3/21 18:09
 * @Description:
 */
public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // dp[i][j] 表示从[0][0]到[i][j]的路径数量
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < n; i++) {
            dp[0][i] = obstacleGrid[0][i] == 1 ? 0 : dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        UniquePathsWithObstacles uniquePathsWithObstacles = new UniquePathsWithObstacles();
        Assert.assertEquals(1, uniquePathsWithObstacles.uniquePathsWithObstacles(new int[][]{
                {0,1},{0,0}
        }));
        Assert.assertEquals(2, uniquePathsWithObstacles.uniquePathsWithObstacles(new int[][]{
                {0,0,0},{0,1,0},{0,0,0}
        }));
        Assert.assertEquals(1, uniquePathsWithObstacles.uniquePathsWithObstacles(new int[][]{
                {0,0,0},
                {0,1,0},
                {0,1,0}
        }));
        Assert.assertEquals(3, uniquePathsWithObstacles.uniquePathsWithObstacles(new int[][]{
                {0,0,0,0},
                {0,1,0,0},
                {0,1,1,0},
                {0,0,0,0}
        }));
    }
}
