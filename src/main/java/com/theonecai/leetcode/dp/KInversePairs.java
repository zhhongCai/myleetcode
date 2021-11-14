package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 629
 */
public class KInversePairs {
    public int kInversePairs(int n, int k) {
        int mod = 1000000007;
        int[][] dp = new int[n + 1][k + 1];
        int[][] preSum = new int[n + 1][k + 1];
        dp[1][0] = 1;
        Arrays.fill(preSum[1], 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                /*for (int kk = 0; kk < i; kk++) {
                    int s = j - (i - 1 - kk);
                    if (s >= 0 && s <= j) {
                        dp[i][j] += dp[i - 1][s];
                        dp[i][j] %= mod;
                    }
                }*/
                dp[i][j] = j < i ? preSum[i - 1][j] : (preSum[i - 1][j] - preSum[i - 1][j - i] + mod) % mod;
                preSum[i][j] = j == 0 ? dp[i][j] : (preSum[i][j - 1] + dp[i][j]) % mod;
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        KInversePairs pairs = new KInversePairs();
        Assert.assertEquals(955735232, pairs.kInversePairs(1000, 500));
        Assert.assertEquals(2, pairs.kInversePairs(3, 2));
    }
}
