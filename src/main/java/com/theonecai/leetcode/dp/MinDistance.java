package com.theonecai.leetcode.dp;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

/**
 * leetcode 72
 */
public class MinDistance {

    public int minDistance(String str, String str2) {
        if (str.length() == 0) {
            return str2.length();
        }
        if (str2.length() == 0) {
            return str.length();
        }
        /**
         * dp[i][j]表示str[0~i-1]和str2[0~j-1]的最小编辑距离
         * 状态转移方程：
         * 如果str[i] != str[j]：a = dp[i - 1][j - 1] or a = dp[i - 1][j - 1] + 1;
         *  dp[i][j] = min(dp[i][j - 1] + 1(插入), a(替换), dp[i - 1][j] + 1(删除))
         */
        int[][] dp = new int[str.length() + 1][str2.length() + 1];
        for (int j =  0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int a =  dp[i - 1][j - 1];
                if (str.charAt(i - 1) != str2.charAt(j - 1)) {
                    a += 1;
                }
                dp[i][j] = Math.min(dp[i][j - 1] + 1, Math.min(a, dp[i - 1][j] + 1));
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            ArrayUtil.print(dp[i]);
//        }
        return dp[str.length()][str2.length()];
    }

    public static void main(String[] args) {
        MinDistance minDistance = new MinDistance();
        Assert.assertEquals(27, minDistance.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis","ultramicroscopically"));
        Assert.assertEquals(1, minDistance.minDistance("ab", "a"));
        Assert.assertEquals(2, minDistance.minDistance("sea", "eat"));
        Assert.assertEquals(0, minDistance.minDistance("", ""));
        Assert.assertEquals(5, minDistance.minDistance("horse", ""));
        Assert.assertEquals(5, minDistance.minDistance("", "abcde"));
        Assert.assertEquals(3, minDistance.minDistance("horse", "ros"));
    }

}
