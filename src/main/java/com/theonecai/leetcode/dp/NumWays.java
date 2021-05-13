package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 1269
 * @Author: theonecai
 * @Date: Create in 5/13/21 22:19
 * @Description:
 */
public class NumWays {

    public int numWays(int steps, int arrLen) {
        int maxIndex = Math.min(steps, arrLen - 1);
        // dp[s][i]表示第s步到i时的种数
        // dp[s][i] = dp[s - 1][i] + dp[s - 1][i - 1] + dp[s - 1][i + 1];
        int[][] dp = new int[steps + 1][maxIndex + 1];
        dp[0][0] = 1;
        int mod = 1000000007;
        for (int s = 1; s <= steps; s++) {
            for (int i = 0; i <= maxIndex; i++) {
                dp[s][i] = dp[s - 1][i];
                if (i > 0) {
                    dp[s][i] = (dp[s][i] + dp[s - 1][i - 1]) % mod;
                }
                if (i < maxIndex) {
                    dp[s][i] = (dp[s][i] + dp[s - 1][i + 1]) % mod;
                }
            }
        }

        return dp[steps][0] % mod;
    }

    public static void main(String[] args) {
        NumWays numWays = new NumWays();
        Assert.assertEquals(4, numWays.numWays(3, 2));
        Assert.assertEquals(2, numWays.numWays(2, 4));
        Assert.assertEquals(8, numWays.numWays(4, 2));
    }
}
