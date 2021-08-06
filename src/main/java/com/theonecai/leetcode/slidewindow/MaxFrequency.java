package com.theonecai.leetcode.slidewindow;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 1838
 * @Author: theonecai
 * @Date: Create in 7/19/21 22:43
 * @Description:
 */
public class MaxFrequency {
    /**
     * 排序+滑动窗口
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int res = 1;
        long sum = 0;
        for (int right = 1; right < n; right++) {
            sum += (nums[right] - nums[right - 1]) * (right - left);
            while(sum > k) {
                sum -= nums[right] - nums[left++];
            }
            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        MaxFrequency maxFrequency = new MaxFrequency();
        Assert.assertEquals(3, maxFrequency.maxFrequency(new int[]{1,2,3,4}, 3));
        Assert.assertEquals(3, maxFrequency.maxFrequency(new int[]{1,2,4}, 5));
        Assert.assertEquals(2, maxFrequency.maxFrequency(new int[]{1,4,8,13}, 5));
        Assert.assertEquals(1, maxFrequency.maxFrequency(new int[]{3,9,6}, 2));
    }
}
