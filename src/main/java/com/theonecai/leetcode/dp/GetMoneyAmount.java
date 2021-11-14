package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 375
 */
public class GetMoneyAmount {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <=n ; len++) {
            for (int left = 1; left + len - 1 <= n; left++) {
                int cost = Integer.MAX_VALUE;
                int right = left + len - 1;
                for (int x = left; x < right; x++) {
                    cost = Math.min(cost, Math.max(dp[left][x - 1], dp[x + 1][right]) + x);
                }
                dp[left][right] = cost;
            }
        }
        return dp[1][n];
    }

    private static int[][] memo = new int[201][201];
    public int getMoneyAmount2(int n) {
        return dfs(1, n);
    }

    private int dfs(int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (memo[left][right] != 0) {
            return memo[left][right];
        }
        int res = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            res = Math.min(res, Math.max(dfs(left, i - 1), dfs(i + 1, right)) + i);
        }
        memo[left][right] = res;
        return res;
    }

    public static void main(String[] args) {
        GetMoneyAmount getMoneyAmount = new GetMoneyAmount();
        Assert.assertEquals(16, getMoneyAmount.getMoneyAmount(10));
        Assert.assertEquals(1, getMoneyAmount.getMoneyAmount(2));
    }
}
