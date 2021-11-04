package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1218
 */
public class LongestSubsequence {

    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        //dp[i]表示i结尾的最长的等差子序列长度
        int[] dp = new int[n];
        dp[0] = 1;
        int res = dp[0];
        int[] indexs = new int[40001];
        Arrays.fill(indexs, -1);
        indexs[arr[0] + 20000] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            int idx = indexs[arr[i] - difference + 20000];
            if (idx != -1) {
                dp[i] = dp[idx] + 1;
            }
            indexs[arr[i] + 20000] = i;
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        LongestSubsequence longestSubsequence = new LongestSubsequence();
        Assert.assertEquals(4, longestSubsequence.longestSubsequence(new int[]{1,2,3,4}, 1));
        Assert.assertEquals(1, longestSubsequence.longestSubsequence(new int[]{1,3,5,7}, 1));
        Assert.assertEquals(4, longestSubsequence.longestSubsequence(new int[]{1,5,7,8,5,3,4,2,1}, -2));
    }
}
