package com.theonecai.algorithms;

import org.junit.Assert;

import java.util.Arrays;

/**
 *  计算两个字符串的编辑距离:
 *  支持操作：插入，替换，删除
 * @Author: theonecai
 * @Date: Create in 2020/8/26 19:52
 * @Description:
 */
public class EditDistance {

    public int backtrace(String str, String str2) {
        if (str2.length() < str.length()) {
            return backtrace(str2, str);
        }
        return backtrace(str, str2, str.length() - 1, str2.length() - 1);
    }

    private int backtrace(String str, String str2, int leftIndex, int rightIndex) {
        if (leftIndex == 0) {
            return rightIndex;
        }
        if (rightIndex == 0) {
            return leftIndex;
        }

        if (str.charAt(leftIndex) == str2.charAt(rightIndex)) {
            return backtrace(str, str2, leftIndex - 1, rightIndex - 1);
        }
        // 插入
        int count = backtrace(str, str2, leftIndex, rightIndex - 1);
        // 替换
        int count2 = backtrace(str, str2, leftIndex - 1, rightIndex - 1);
        // 删除
        int count3 = backtrace(str, str2, leftIndex - 1, rightIndex);

        return Math.min(Math.min(count, count2), count3) + 1;
    }

    public int backtraceWithMemo(String str, String str2) {
        if (str2.length() < str.length()) {
            return backtrace(str2, str);
        }
        int[][] memo = new int[str.length() + 1][str2.length() + 1];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return backtraceWithMemo(str, str2, str.length() - 1, str2.length() - 1, memo);
    }

    private int backtraceWithMemo(String str, String str2, int leftIndex, int rightIndex, int[][] memo) {
        if (memo[leftIndex][rightIndex] != -1) {
            return memo[leftIndex][rightIndex];
        }
        if (leftIndex == 0) {
            memo[leftIndex][rightIndex] = rightIndex;
            return rightIndex;
        }
        if (rightIndex == 0) {
            memo[leftIndex][rightIndex] = leftIndex;
            return leftIndex;
        }

        if (str.charAt(leftIndex) == str2.charAt(rightIndex)) {
            return backtraceWithMemo(str, str2, leftIndex - 1, rightIndex - 1, memo);
        }
        // 插入
        int count = 1 + backtraceWithMemo(str, str2, leftIndex, rightIndex - 1, memo);
        // 替换
        int count2 = 1 + backtraceWithMemo(str, str2, leftIndex - 1, rightIndex - 1, memo);
        // 删除
        int count3 = 1 + backtraceWithMemo(str, str2, leftIndex - 1, rightIndex, memo);

        int c = Math.min(Math.min(count, count2), count3);
        memo[leftIndex][rightIndex] = c;
        return c;
    }

    public int dp(String str, String str2) {
        if (str2.length() < str.length()) {
            return backtrace(str2, str);
        }
        /**
         * dp[i][j]表示str[0~i]和str2[0~j]的最小编辑距离
         * 状态转移方程：
         * 如果str[i] == str[j]：dp[i][j] = dp[i - 1][j - 1];
         * 如果str[i] != str[j]: dp[i][j] = min(dp[i][j - 1](插入), dp[i - 1][j - 1](替换), dp[i - 1][j](删除)) + 1
         */
        int[][] dp = new int[str.length()][str2.length()];
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = str.charAt(0) == str2.charAt(0) ? j : j + 1;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = str.charAt(0) == str2.charAt(0) ? i : i + 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            ArrayUtil.print(dp[i]);
//        }
        return dp[str.length() - 1][str2.length() - 1];
    }


    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        String a = "acasdffsdabdiefhliasnkdfsdfisdfnasdfpfdfe";
        String b = "abcasdffsdssielashilmnpqwexweiosdlxcsdfdef";
        Assert.assertEquals(25, editDistance.backtraceWithMemo(a, b));
        Assert.assertEquals(25, editDistance.dp(a, b));
//        Assert.assertEquals(25, editDistance.backtrace(a, b));
    }
}
