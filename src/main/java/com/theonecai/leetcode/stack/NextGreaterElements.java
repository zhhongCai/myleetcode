package com.theonecai.leetcode.stack;

import com.theonecai.algorithms.ArrayUtil;

/**
 * leetcode 503
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/8 19:47
 * @Description:
 */
public class NextGreaterElements {

    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        // 递减栈,存索引
        int[] descStack = new int[nums.length];
        int[] result = new int[nums.length];
        int top = -1;

        int j;
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            j = i % nums.length;
            while (top > -1 &&  nums[descStack[top]] <= nums[j]) {
                top--;
            }
            result[j] = top < 0 ? -1 : nums[descStack[top]];
            descStack[++top] = j;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 3, 6, 8};
        NextGreaterElements nextGreaterElements = new NextGreaterElements();
        ArrayUtil.print(nextGreaterElements.nextGreaterElements(nums));
    }
}
