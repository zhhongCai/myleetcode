package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 377
 */
public class CombinationSum4 {

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        // dp[i] 表示和为i的组合数
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i < num) {
                    break;
                }
                dp[i] += dp[i - num];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        CombinationSum4 combinationSum4 = new CombinationSum4();

        Assert.assertEquals(44, combinationSum4.combinationSum4(new int[]{1,2,3}, 7));
        Assert.assertEquals(7, combinationSum4.combinationSum4(new int[]{1,2,3}, 4));
        Assert.assertEquals(0, combinationSum4.combinationSum4(new int[]{5,7}, 1));
    }
}
