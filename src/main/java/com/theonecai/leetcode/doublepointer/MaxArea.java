package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

/**
 *leetcode 11
 * @Author: theonecai
 * @Date: Create in 5/23/21 10:17
 * @Description:
 */
public class MaxArea {
    /**
     * 双指针
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        int h = 0;
        int w = 0;
        while (left <= right) {
            w = right - left;
            if (height[left] < height[right]) {
                h = height[left];
                left++;
            } else {
                h = height[right];
                right--;
            }
            max = Math.max(max, w * h);
        }

        return max;
    }

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        Assert.assertEquals(49, maxArea.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        Assert.assertEquals(1, maxArea.maxArea(new int[]{1,1}));
        Assert.assertEquals(16, maxArea.maxArea(new int[]{4,3,2,1,4}));
        Assert.assertEquals(2, maxArea.maxArea(new int[]{1,2,1}));
    }
}
