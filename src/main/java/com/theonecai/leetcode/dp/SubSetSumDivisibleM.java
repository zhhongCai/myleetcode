package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定数组nums，m,判断是否存在可被m整除的子序列和
 * https://www.geeksforgeeks.org/subset-sum-divisible-m/
 *
 * @Author: theonecai
 * @Date: Create in 2020/9/15 16:52
 * @Description:
 */
public class SubSetSumDivisibleM {

    public boolean subSetSumDivisibleM(int[] nums, int m) {
        if (m == 1 || nums.length > m) {
            return true;
        }

        /**
         * dp[i]表示子序列和除以m的余数是否存在(i = sum % m)
         */
        boolean[] dp = new boolean[m];
        Arrays.fill(dp, false);
        Set<Integer> tmp = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (dp[0]) {
                return true;
            }

            for (int j = 0; j < m; j++) {
                if (dp[j]) {
                    tmp.add((j + nums[i]) % m);
                }
            }

            tmp.forEach(s -> dp[s] = true);

            tmp.clear();
            dp[nums[i] % m] = true;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        SubSetSumDivisibleM subSetSumDivisibleM = new SubSetSumDivisibleM();
        int[] nums = {3, 1, 7, 5};
        Assert.assertTrue(subSetSumDivisibleM.subSetSumDivisibleM(nums, 6));
    }
}
