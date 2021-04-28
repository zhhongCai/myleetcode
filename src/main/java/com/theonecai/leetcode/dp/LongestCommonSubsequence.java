package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 1143
 * @Author: theonecai
 * @Date: Create in 2021/4/3 15:59
 * @Description:
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int len = text1.length();
        int len2 = text2.length();
        // dp[i][j]表示text1(0~i-1)字符和text2(0-~j-1)字符的最大公共子序列长度
        int[][] dp = new int[len + 1][len2 + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return  dp[len][len2];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence subsequence = new LongestCommonSubsequence();
        Assert.assertEquals(0, subsequence.longestCommonSubsequence("a", "b"));
        Assert.assertEquals(3, subsequence.longestCommonSubsequence("abcde", "ace"));
        Assert.assertEquals(3, subsequence.longestCommonSubsequence("abc", "abc"));
        Assert.assertEquals(0, subsequence.longestCommonSubsequence("abc", "def"));
    }
}
