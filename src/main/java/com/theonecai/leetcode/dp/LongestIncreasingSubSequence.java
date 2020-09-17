package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 求最长递增子串:
 * https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
 *
 * @Author: theonecai
 * @Date: Create in 2020/9/15 20:37
 * @Description:
 */
public class LongestIncreasingSubSequence {

    /**
     * list[i]表示nums[0...i]包括nums[i]的最长递增子串长度：
     * list[i] = max(list[j]) + 1, 其中0<= j < i 且 nums[i] > nums[j];
     * 如果j不存在，list[i] = 1
     * @param nums
     * @return
     */
    public int longestIncreasingSubSequenceRecursive(int[] nums) {
        return list(nums, nums.length - 1);
    }

    private int list(int[] nums, int i) {
        if (i == 0) {
            return 1;
        }

        int res;
        int max = 1;
        // 求出0<=j<i中list[j],如果nums[i] > nums[j],更新list[j](即max)
        for (int j = 0; j < i; j++) {
            res = list(nums, j);
            if (nums[j] < nums[i] && res + 1 > max) {
                max = res + 1;
            }
        }

        return max;
    }

    /**
     * list[i]表示nums[0...i]包括nums[i]的最长递增子串长度：
     * list[i] = max(list[j]) + 1, 其中0<= j < i 且 nums[i] > nums[j];
     * 如果j不存在，list[i] = 1
     * @param nums
     * @return
     */
    public int longestIncreasingSubSequenceDp(int[] nums) {
        int max = 1;
        int[] list = new int[nums.length];
        Arrays.fill(list, 1);

        int res;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                res = list[j] + 1;
                if (nums[j] < nums[i] && res > list[i]) {
                    list[i] = res;
                }
            }
        }

        for (int count : list) {
            max = Math.max(max, count);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestIncreasingSubSequence increasingSubSequence = new LongestIncreasingSubSequence();
        Assert.assertEquals(6, increasingSubSequence.longestIncreasingSubSequenceRecursive(
                new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80}));
        Assert.assertEquals(6, increasingSubSequence.longestIncreasingSubSequenceDp(
                new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80}));
    }
}
