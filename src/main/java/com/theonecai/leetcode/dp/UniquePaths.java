package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 62
 * @Author: theonnecai
 * @Date: Create in 2021/3/21 16:10
 * @Description:
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[j] = dp[j];
                if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n- 1];
    }

    public int uniquePaths2(int m, int n) {
        // dp[i][j] 表示从[0][0]到[i][j]的路径数量
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        Assert.assertEquals(1, uniquePaths.uniquePaths(1, 1));
        Assert.assertEquals(28, uniquePaths.uniquePaths(3, 7));
        Assert.assertEquals(3, uniquePaths.uniquePaths(3, 2));
        Assert.assertEquals(28, uniquePaths.uniquePaths(7, 3));
        Assert.assertEquals(252, uniquePaths.uniquePaths(6, 6));
        Assert.assertEquals(6, uniquePaths.uniquePaths(3, 3));
    }
}
