package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 1856
 * @Author: zhenghong.cai
 * @Date: Create in 5/12/21 20:36
 * @Description:
 */
public class MaxSumMinProduct {

    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        long[] preSum = new long[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> incrStack = new Stack<>();

        preSum[0] = nums[0];
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                preSum[i] = preSum[i - 1] + nums[i];
            }
            while (!incrStack.isEmpty() && nums[i] <= nums[incrStack.peek()]) {
                incrStack.pop();
            }
            left[i] = incrStack.isEmpty() ? -1 : incrStack.peek();
            incrStack.push(i);
        }
        incrStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!incrStack.isEmpty() && nums[i] <= nums[incrStack.peek()]) {
                incrStack.pop();
            }
            right[i] = incrStack.isEmpty() ? n - 1 : incrStack.peek() - 1;
            incrStack.push(i);
        }
        long result = 0L;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, nums[i] * (preSum[right[i]] - (left[i] == -1 ? 0 : preSum[left[i]])));
        }

        return (int) (result % 1000000007L);
    }

    public static void main(String[] args) {
        MaxSumMinProduct maxSumMinProduct = new MaxSumMinProduct();
        Assert.assertEquals(36, maxSumMinProduct.maxSumMinProduct(new int[]{5,4,3,2,1}));
        Assert.assertEquals(36, maxSumMinProduct.maxSumMinProduct(new int[]{1,2,3,4,5}));
        Assert.assertEquals(14, maxSumMinProduct.maxSumMinProduct(new int[]{1,2,3,2}));
        Assert.assertEquals(18, maxSumMinProduct.maxSumMinProduct(new int[]{2,3,3,1,2}));
        Assert.assertEquals(60, maxSumMinProduct.maxSumMinProduct(new int[]{3,1,5,6,4,2}));
    }
}
