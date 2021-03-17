package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 115
 * @Author: theonecai
 * @Date: Create in 2021/3/17 20:45
 * @Description:
 */
public class NumDistinct {
    public int numDistinct4(String s, String t) {
        if (s.length() == t.length()) {
            return s.equals(t) ? 1 : 0;
        }
        if (s.length() < t.length()) {
            return 0;
        }

        return dp(s, t);
    }

    public int numDistinct(String s, String t) {
        if (s.length() == t.length()) {
            return s.equals(t) ? 1 : 0;
        }
        if (s.length() < t.length()) {
            return 0;
        }
        memo = new int[s.length() + 1][t.length() + 1];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dfs2(s, t, 0, 0);
    }

    public int numDistinct3(String s, String t) {
        if (s.length() == t.length()) {
            return s.equals(t) ? 1 : 0;
        }
        if (s.length() < t.length()) {
            return 0;
        }
        return dfs(s, t, 0,0);
    }

    /**
     * 从s中取s[i]去匹配t[j]
     * @param s
     * @param t
     * @param i
     * @param j
     * @return
     */
    private int dfs(String s, String t, int i, int j) {
        if (j >= t.length()) {
            return 1;
        }
        if (i >= s.length()) {
            return 0;
        }
        if (s.charAt(i) == t.charAt(j)) {
            return dfs(s, t, i + 1, j + 1) + dfs(s, t, i + 1, j);
        }
        return dfs(s, t, i + 1, j);
    }

    private int[][] memo;

    private int dfs2(String s, String t, int i, int j) {
        if (j >= t.length()) {
            return 1;
        }
        if (i >= s.length()) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s.charAt(i) == t.charAt(j)) {
            memo[i][j] = dfs2(s, t, i + 1, j + 1) + dfs2(s, t, i + 1, j);
        } else {

            memo[i][j] = dfs2(s, t, i + 1, j);
        }
        return memo[i][j];
    }

    private int dp(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = 0;
                } else {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        return dp[s.length()][t.length()];
    }

    public static void main(String[] args) {
        NumDistinct numDistinct = new NumDistinct();
        Assert.assertEquals(700531452, numDistinct.numDistinct("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc","bcddceeeebecbc"));
        Assert.assertEquals(3, numDistinct.numDistinct("rabbbit", "rabbit"));
        Assert.assertEquals(5, numDistinct.numDistinct("babgbag", "bag"));
    }
}
