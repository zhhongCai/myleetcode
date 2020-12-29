package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 96
 */
public class NumTrees {
    public int numTrees(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                count += dp[j - 1] * dp[i - j];
            }
            dp[i] = count;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        NumTrees numTrees = new NumTrees();
        Assert.assertEquals(5, numTrees.numTrees(3));
        Assert.assertEquals(1430, numTrees.numTrees(8));
        Assert.assertEquals(300814726, numTrees.numTrees(32));
    }
}
