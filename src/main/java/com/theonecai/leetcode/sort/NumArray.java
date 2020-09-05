package com.theonecai.leetcode.sort;

import org.junit.Assert;

/**
 * leetcode 307
 * @Author: theonecai
 * @Date: Create in 2020/9/3 13:24
 * @Description:
 */
public class NumArray {

    private int[] biTree;
    private int[] nums;

    public NumArray(int[] nums) {
        biTree = new int[nums.length + 1];
        this.nums = nums;

        // 前缀和
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            biTree[i] = prefixSum[i] - prefixSum[i - lowbit(i)];
        }
    }

    private int lowbit(int i) {
        return i & (-i);
    }

    public int sumRange(int i, int j) {
        return getSum(j + 1) - getSum(i);
    }

    private int getSum(int i) {
        int idx = i;
        int sum = 0;
        while (idx > 0) {
            sum += biTree[idx];
            idx -= lowbit(idx);
        }
        return sum;
    }

    public void update(int i, int value) {
        int idx = i + 1;
        int diff = this.nums[i] - value;
        this.nums[i] = value;
        while (idx < biTree.length) {
            biTree[idx] -= diff;
            idx += lowbit(idx);
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 5, 8};
        NumArray numArray = new NumArray(nums);
        Assert.assertEquals(3, numArray.sumRange(0, 0));
        Assert.assertEquals(5, numArray.sumRange(1, 1));
        Assert.assertEquals(8, numArray.sumRange(2, 2));
        Assert.assertEquals(13, numArray.sumRange(1, 2));
        Assert.assertEquals(8, numArray.sumRange(0, 1));
        Assert.assertEquals(16, numArray.sumRange(0, 2));

        numArray.update(1, 5);
        Assert.assertEquals(16, numArray.sumRange(0, 2));
        Assert.assertEquals(13, numArray.sumRange(1, 2));
        Assert.assertEquals(5, numArray.sumRange(1, 1));

        // 7 10 8
        numArray.update(0, 4);
        Assert.assertEquals(4, numArray.sumRange(0, 0));
        Assert.assertEquals(5, numArray.sumRange(1, 1));
        Assert.assertEquals(8, numArray.sumRange(2, 2));
        Assert.assertEquals(13, numArray.sumRange(1, 2));
        Assert.assertEquals(9, numArray.sumRange(0, 1));
        Assert.assertEquals(17, numArray.sumRange(0, 2));
    }
}
