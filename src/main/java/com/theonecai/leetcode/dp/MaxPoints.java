package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * 5815
 * @Author: theonecai
 * @Date: Create in 7/18/21 20:13
 * @Description:
 */
public class MaxPoints {
    public long maxPoints(int[][] points) {
        int row = points.length;
        int col = points[0].length;
        long[] dp = new long[row * col];
        for (int i = 0; i < col; i++) {
            dp[i] = points[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int idx = i * col + j;
                for (int k = 0; k < col; k++) {
                    int pre = (i - 1) * col + k;
                    dp[idx] = Math.max(dp[idx], points[i][j] + dp[pre] - Math.abs(j - k));
                }
            }
        }
        long res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        MaxPoints maxPoints = new MaxPoints();
        Assert.assertEquals(9, maxPoints.maxPoints(new int[][]{
                {1,2,3},{1,5,1},{3,1,1}
        }));
        Assert.assertEquals(11, maxPoints.maxPoints(new int[][]{
                {1,5},{2,3},{4,2}
        }));
    }
}
