package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 516
 */
public class LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // dp[i][j]表示i~j子序列中最长的回文序列的长度
        // if s[i] == s[j] dp[i][j] = dp[i + 1][j - 1] + 2
        // 否则 dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char ch = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                if (ch == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        LongestPalindromeSubseq subseq = new LongestPalindromeSubseq();
        Assert.assertEquals(4, subseq.longestPalindromeSubseq("bbbab"));
    }
}
