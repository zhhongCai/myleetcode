package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * 53
 * @Author: theonecai
 * @Date: Create in 2021/3/21 14:42
 * @Description:
 */
public class MaxSubArray {
    public int maxSubArray2(int[] nums) {
        // dp[i]表示以nums[i]结尾的连续子串的最大和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int maxVal = nums[0];
        for (int sum : dp) {
            maxVal = Math.max(maxVal, sum);
        }
        return maxVal;
    }

    public int maxSubArray(int[] nums) {
        int pre = 0;
        int maxVal = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            maxVal = Math.max(maxVal, pre);
        }
        return maxVal;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        Assert.assertEquals(maxSubArray.maxSubArray(arr), maxSubArray.maxSubArray(arr));
    }
}
