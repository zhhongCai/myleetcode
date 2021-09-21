package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 650
 */
public class MinSteps {
    public int minSteps(int n) {
        int res = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                n /= i;
                res += i;
                System.out.println(i);
            }
        }
        if (n > 1) {
            res += n;
        }

        return res;
    }

    public int minSteps2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                    dp[i] = Math.min(dp[i], dp[i / j] + j);
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        MinSteps minSteps = new MinSteps();
        Assert.assertEquals(0, minSteps.minSteps(1));
        Assert.assertEquals(2, minSteps.minSteps(2));
        Assert.assertEquals(13, minSteps.minSteps(13));
        Assert.assertEquals(18, minSteps.minSteps(132));
    }
}
