package com.theonecai.geekforgeek.dp;

import org.junit.Assert;

/**
 * 求两个数组最长公共连续子串长度:
 * https://www.geeksforgeeks.org/longest-common-subarray-in-the-given-two-arrays/?ref=leftbar-rightbar
 *
 * @Author: theonecai
 * @Date: Create in 2020/9/15 19:16
 * @Description:
 */
public class LongestCommonSubArray {

    public int countLongestCommonSubArray(int[] a, int[] b) {
        /**
         * count[i][j] 表示 a[i...]和b[j...]的最长公共子串的长度：
         * 对于任意i,j, a[i]=b[j]时，count[i][j] = count[i + 1][j + 1] + 1
         * count中最大值即为答案
         */
        int[][] count = new int[a.length + 1][b.length + 1];

        int max = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = b.length - 1; j >= 0; j--) {
                if (a[i] == b[j]) {
                    count[i][j] = count[i + 1][j + 1] + 1;
                    max = Math.max(count[i][j], max);
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,6,7,2};
        int[] b = {3,4,6,7,2,1};
        LongestCommonSubArray longestCommonSubArray = new LongestCommonSubArray();
        Assert.assertEquals(5, longestCommonSubArray.countLongestCommonSubArray(a, b));
    }
}
