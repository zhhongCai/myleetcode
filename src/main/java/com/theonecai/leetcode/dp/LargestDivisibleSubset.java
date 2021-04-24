package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * leetcode 368
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        // dp[i][0]表示nums[0~i]中满足条件的的最长个数
        // dp[i][1]表示nums[0~i]中满足条件的的最长个数时前一个数的索引
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        int max = dp[0][0];
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
            for (int j = 0; nums[j] * 2L <= nums[i]; j++) {
                if (nums[i] % nums[j] == 0 && dp[i][0] < dp[j][0] + 1) {
                    dp[i][0] = dp[j][0] + 1;
                    dp[i][1] = j;
                }
            }
            if (max < dp[i][0]) {
                max = dp[i][0];
                index = i;
            }
        }

        int[] arr = new int[max];
        int i = max - 1;
        while (i >= 0) {
            arr[i--] = nums[index];
            index = dp[index][1];
        }

        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        LargestDivisibleSubset largestDivisibleSubset = new LargestDivisibleSubset();

        Assert.assertArrayEquals(new int[]{4,8,240}, largestDivisibleSubset.largestDivisibleSubset(new int[]{4,8,10,240})
                .stream().mapToInt(Integer::intValue).toArray());
        Assert.assertArrayEquals(new int[]{1,2}, largestDivisibleSubset.largestDivisibleSubset(new int[]{1,2,3})
                .stream().mapToInt(Integer::intValue).toArray());
    }
}
