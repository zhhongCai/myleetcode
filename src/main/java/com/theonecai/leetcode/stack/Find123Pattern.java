package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/2 11:33
 * @Description:
 */
public class Find123Pattern {

    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(nums[i], min[i - 1]);
        }

        int ai;
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[nums.length - 1]);
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > min[i]) {
                ai = nums[i];
                while (!stack.isEmpty() && min[i] >= stack.peek()) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < ai) {
                    return true;
                }

                stack.push(ai);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Find123Pattern find = new Find123Pattern();
        int[] nums = {1 ,2, 3, 4};
        Assert.assertFalse(find.find132pattern(nums));

        int[]  nums2 = {1 ,2, 0, 4, 3};
        Assert.assertTrue(find.find132pattern(nums2));

        int[]  nums3 = {-1, 3, 2, 0};
        Assert.assertTrue(find.find132pattern(nums3));

        int[]  nums4 = {3, 5, 0, 3, 4};
        Assert.assertTrue(find.find132pattern(nums4));

        int[]  nums5 = {-2,1,2,-2,1,2};
        Assert.assertTrue(find.find132pattern(nums5));
    }
}
