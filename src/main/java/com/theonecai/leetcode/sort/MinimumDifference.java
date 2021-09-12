package com.theonecai.leetcode.sort;

import java.util.Arrays;

/**
 * leetcode 1984
 */
public class MinimumDifference {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for (int left = 0, right = left + k - 1; right < nums.length; left++, right++) {
            res = Math.min(res, nums[right] - nums[left]);
        }
        return res;
    }
}
