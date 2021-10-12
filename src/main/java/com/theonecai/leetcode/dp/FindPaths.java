package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 576
 */
public class FindPaths {
    int mod = 1000000007;
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        //dp[i][j] 表示位置i经过j步的路径数量
        int[][] dp = new int[m * n][maxMove + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= maxMove; j++) {
                dp[getIndex(n, 0, i)][j]++;
                dp[getIndex(n, m - 1, i)][j]++;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= maxMove; j++) {
                dp[getIndex(n, i, 0)][j]++;
                dp[getIndex(n, i, n - 1)][j]++;
            }
        }

        int[][] directions = new int[][]{
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1},
        };

        for (int j = 1; j <= maxMove; j++) {
            for (int i = 0; i < dp.length; i++) {
                int[] idx = getRowColIdx(n, i);
                for (int[] direction : directions) {
                    int r = idx[0] + direction[0];
                    int c = idx[1] + direction[1];
                    if (0 <= r && r < m && 0 <= c && c < n) {
                        dp[i][j] += dp[getIndex(n, r, c)][j - 1];
                        dp[i][j] %= mod;
                    }
                }
            }
        }

        return dp[getIndex(n, startRow, startColumn)][maxMove];
    }

    private int getIndex(int cols, int i, int j) {
        return i * cols + j;
    }

    private int[] getRowColIdx(int cols, int idx) {
        return new int[]{idx / cols, idx % cols};
    }

    public static void main(String[] args) {
        FindPaths findPaths = new FindPaths();
        Assert.assertEquals(6, findPaths.findPaths(2, 2, 2, 0, 0));
        Assert.assertEquals(12, findPaths.findPaths(1, 3, 3, 0, 1));
        Assert.assertEquals(4, findPaths.findPaths(1, 1, 3, 0, 0));
        Assert.assertEquals(3999, findPaths.findPaths(1, 10, 13, 0, 0));
    }
}
