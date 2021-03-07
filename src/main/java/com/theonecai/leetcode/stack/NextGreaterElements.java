package com.theonecai.leetcode.stack;

import com.theonecai.algorithms.ArrayUtil;

import java.util.Arrays;

/**
 * leetcode 503
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/8 19:47
 * @Description:
 */
public class NextGreaterElements {

    public int[] nextGreaterElements2(int[] nums) {
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

    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        Num[] data = new Num[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = new Num(nums[i], i);
        }
        Arrays.sort(data);

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = findNextgreater(data, nums, i);
        }

        return result;
    }

    private int findNextgreater(Num[] data, int[] nums, int current) {
        if (data[data.length - 1].value == nums[current]) {
            return -1;
        }
        Num min = null;
        Num min2 = null;
        int index = Arrays.binarySearch(data, new Num(nums[current], current));
        for (int i = index + 1; i < data.length; i++) {
            if (data[i].value == nums[current]) {
                continue;
            }
            if (data[i].index < current) {
                if (min2 == null) {
                    min2 = data[i];
                } else {
                    min2 = data[i].index < min2.index ? data[i] : min2;
                }
            } else {
                if (min == null) {
                    min = data[i];
                } else {
                    min = data[i].index < min.index ? data[i] : min;
                }
            }
        }

        return min == null ? (min2 == null ? -1 : min2.value) : min.value;
    }

    private static class Num implements Comparable<Num> {
        private int index;
        private int value;

        public Num(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Num o) {
            int result = this.value - o.value;
            if (result == 0) {
                result = this.index - o.index;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 3, 6, 8};
        NextGreaterElements nextGreaterElements = new NextGreaterElements();
        ArrayUtil.print(nextGreaterElements.nextGreaterElements(nums));
        ArrayUtil.print(nextGreaterElements.nextGreaterElements(new int[] {1,2,3,4,5,6,5,4,5,1,2,3}));
    }
}
