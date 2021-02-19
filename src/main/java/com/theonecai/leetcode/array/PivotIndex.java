package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 * leetcode 724
 */
public class PivotIndex {

    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            int left = i > 0 ? prefixSum[i - 1] : 0;
            int right = i < nums.length - 1 ? (prefixSum[nums.length - 1] - prefixSum[i]) : 0;
            if (left == right) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        PivotIndex p = new PivotIndex();
        Assert.assertEquals(3, p.pivotIndex(new int[] {1, 7, 3, 6, 5, 6}));
        Assert.assertEquals(-1, p.pivotIndex(new int[] {1, 2, 3}));
        Assert.assertEquals(0, p.pivotIndex(new int[] {0,0,0}));
        Assert.assertEquals(0, p.pivotIndex(new int[] {2, 1, -1}));
        Assert.assertEquals(4, p.pivotIndex(new int[] {0, 0, 0, 0, 1}));
    }
}
