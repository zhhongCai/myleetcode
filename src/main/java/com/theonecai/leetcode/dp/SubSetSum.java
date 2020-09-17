package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 给定数组，sum,判断是否存在子集合其和为sum:
 * https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 *
 * @Author: theonecai
 * @Date: Create in 2020/9/15 14:29
 * @Description:
 */
public class SubSetSum {

    public boolean subSetSumRecursive(int[] nums, int sum) {
        return isSubSetSum(nums, nums.length, sum);
    }

    private boolean isSubSetSum(int[] nums, int length, int sum) {
        if (sum == 0) {
            return true;
        }
        if (length == 0) {
            return false;
        }
        if (nums[length - 1] > sum) {
            return isSubSetSum(nums, length - 1, sum);
        }

        return isSubSetSum(nums, length - 1, sum) ||
                isSubSetSum(nums, length - 1, sum - nums[length - 1]);
    }

    public boolean subSetSumDp(int[] nums, int sum) {

        /**
         * dp[i][s] = true,表示nums[0...i]中存在子集合其和为s:
         * 如果nums[i] > s, dp[i][s] = dp[i - 1][s]
         * 否则，dp[i][s] = dp[i - 1][s] || dp[i - 1][s - nums[i]]
         */
        boolean[][] dp = new boolean[nums.length][sum + 1];

        // sum == 0
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        // nums.length() == 0
        Arrays.fill(dp[0], false);

        for (int i = 1; i < nums.length; i++) {
            for (int s = 1; s <= sum; s++) {
                dp[i][s] = dp[i - 1][s];
                if (nums[i] <= s) {
                    dp[i][s] = dp[i - 1][s] || dp[i - 1][s - nums[i]];
                }
            }
        }

        return dp[nums.length - 1][sum];
    }

    /**
     * 优化空间 :
     * https://www.geeksforgeeks.org/subset-sum-problem-osum-space/
     *
     * @param nums
     * @param sum
     * @return
     */
    public boolean subSetSumDp2(int[] nums, int sum) {

        /**
         * dp[i][s]只与dp[i-1][?]有关
         * 如果nums[i%2] > s, dp[i%2][s] = dp[(i - 1)%2][s]
         * 否则，dp[i%2][s] = dp[(i - 1)%2][s] || dp[(i - 1)%2][s - nums[i]]
         */
        boolean[][] dp = new boolean[2][sum + 1];

        for (int i = 0; i < nums.length; i++) {
            for (int s = 0; s <= sum; s++) {
                if (i == 0) {
                    dp[0][s] = false;
                } else if (s == 0) {
                    dp[i % 2][s] = true;
                } else {
                    dp[i % 2][s] = dp[(i - 1) % 2][s];
                    if (nums[i] <= s) {
                        dp[i % 2][s] = dp[(i - 1) % 2][s] || dp[(i - 1) % 2][s - nums[i]];
                    }
                }
            }
        }

        return dp[nums.length % 2][sum];
    }


    public static void main(String[] args) {
        SubSetSum subSetSum = new SubSetSum();
        int[] nums= {3, 34, 4, 12, 5, 2};
        Assert.assertTrue(subSetSum.subSetSumRecursive(nums, 9));
        Assert.assertTrue(subSetSum.subSetSumDp(nums, 9));
        Assert.assertTrue(subSetSum.subSetSumDp2(nums, 9));
    }

}
