package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 740
 * @Author: theonecai
 * @Date: Create in 5/5/21 09:40
 * @Description:
 */
public class DeleteAndEarn {

    public int deleteAndEarn(int[] nums) {

        Arrays.sort(nums);

        // dp[i] = max(dp[i], dp[j] + nums[i]) ,0 <= j < i 且 nums[i] != nums[j] + 1
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] + 1 != nums[i]) {
                    dp[i] = Math.max(dp[j] + nums[i], dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        DeleteAndEarn deleteAndEarn = new DeleteAndEarn();
        Assert.assertEquals(18, deleteAndEarn.deleteAndEarn(new int[]{1,1,1,2,4,5,5,5,6}));
        Assert.assertEquals(6, deleteAndEarn.deleteAndEarn(new int[]{3,4,2}));
        Assert.assertEquals(6, deleteAndEarn.deleteAndEarn(new int[]{2,3,4,3}));
        Assert.assertEquals(9, deleteAndEarn.deleteAndEarn(new int[]{2,3,2,3,4,3}));
        Assert.assertEquals(6, deleteAndEarn.deleteAndEarn(new int[]{1,1,1,1,1,1}));
        Assert.assertEquals(15, deleteAndEarn.deleteAndEarn(new int[]{1,5,9}));
        Assert.assertEquals(1, deleteAndEarn.deleteAndEarn(new int[]{1}));
    }
}
