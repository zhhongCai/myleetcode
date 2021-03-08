package com.theonecai.leetcode.backtrace;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 132
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/24 21:31
 * @Description:
 */
public class PalindromeStrII {

    private boolean[][] dp;

    public int minCut(String s) {
        calcIsPalindrome(s);

        /**
         * f[i] 表示s[0~i]的最少分割次数
         * f[i] =(j from 0 to i-1 min(f[j])) + 1, 如果s[j~i]为回文串
         */
        int[] f = new int[s.length()];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < s.length(); i++) {
            if (dp[0][i]) {
                f[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (dp[j + 1][i]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }

        return f[s.length() - 1];
    }

    /**
     * 计算dp[i][j]: 表示s[i~j]是否为回文串
     * @param s
     */
    private void calcIsPalindrome(String s) {
        dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], true);
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
            }
        }
    }

    public static void main(String[] args) {
        PalindromeStrII palindromeStr = new PalindromeStrII();
        Assert.assertEquals(1, palindromeStr.minCut("aaaab"));
        Assert.assertEquals(0, palindromeStr.minCut("ababababababababababababcbabababababababababababa"));
    }
}
