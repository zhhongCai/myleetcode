package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 1449
 * @Author: theonecai
 * @Date: Create in 6/12/21 22:52
 * @Description:
 */
public class LargestNumber {

    public String largestNumber(int[] cost, int target) {
        // dp[i] 表示成本为i时的数的最大长度
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int i = 1; i < 10; i++) {
            int c = cost[i - 1];
            for (int j = c; j <= target; j++) {
                dp[j] = Math.max(dp[j], dp[j - c] + 1);
            }
        }
        if (dp[target] < 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 9, j = target; i > 0; i--) {
            int c = cost[i - 1];
            while (j >= c && dp[j] == dp[j - c] + 1) {
                res.append(i);
                j -= c;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        LargestNumber number = new LargestNumber();
        Assert.assertEquals("0", number.largestNumber(new int[]{2,4,6,2,4,6,4,4,4}, 5));
        Assert.assertEquals("7772", number.largestNumber(new int[]{4,3,2,5,6,7,2,5,5}, 9));
        Assert.assertEquals("85", number.largestNumber(new int[]{7,6,5,5,5,6,8,7,8}, 12));
        Assert.assertEquals("32211", number.largestNumber(new int[]{6,10,15,40,40,40,40,40,40}, 47));
    }
}
