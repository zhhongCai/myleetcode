package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 1035
 * @Author: theonecai
 * @Date: Create in 5/22/21 21:35
 * @Description:
 */
public class MaxUncrossedLines {

    /**
     * 最长公共子序列
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        MaxUncrossedLines maxUncrossedLines = new MaxUncrossedLines();
        Assert.assertEquals(3, maxUncrossedLines.maxUncrossedLines(new int[]{
                2,5,1,2,5
        }, new int[]{
                10,5,2,1,5,2
        }));
        Assert.assertEquals(2, maxUncrossedLines.maxUncrossedLines(new int[]{
                1,3,7,1,7,5
        }, new int[]{
                1,9,2,5,1
        }));
    }
}
