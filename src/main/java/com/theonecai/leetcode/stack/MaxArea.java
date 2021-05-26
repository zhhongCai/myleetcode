package com.theonecai.leetcode.stack;

import java.util.Stack;

/**
 * 11
 * @Author: theonecai
 * @Date: Create in 5/23/21 10:17
 * @Description:
 */
public class MaxArea {
    /**
     * 单调栈
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        Stack<Integer> incStack = new Stack<>();
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (!incStack.isEmpty() && height[incStack.peek()] >= height[i]) {
                incStack.push(i);
            } else {
                max = Math.max(max, Math.min(height[incStack.peek()], height[i]) * (i - incStack.peek()));
            }
        }

        return max;
    }
}
