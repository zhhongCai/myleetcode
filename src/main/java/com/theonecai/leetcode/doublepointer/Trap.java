package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

/**
 * leetcode ms 17.21
 * @Author: theonecai
 * @Date: Create in 2021/4/2 20:17
 * @Description:
 */
public class Trap {

    public int trap(int[] height) {
        int len = height.length;
        int left = -1;
        int right = -1;
        int rangeSum = 0;
        int result = 0;
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                if (i < len - 1 && height[i + 1] < height[i]) {
                    left = i;
                }
            } else {
                if (i < len - 1 && height[i] >= height[i - 1] && height[i] >= height[i + 1]) {
                    if (left != -1 && right <= left) {
                        right = i;
                        int j = right + 1;
                        if (height[left] >= height[right]) {
                            for (; j < len; j++) {
                                if (height[j] >= height[right]) {
                                    right = j;
                                }
                                if (height[j] >= height[left]) {
                                    break;
                                }
                            }
                        }
                        rangeSum = 0;
                        int min = Math.min(height[left], height[right]);
                        for (j = left + 1; j < right; j++) {
                            rangeSum += Math.min(height[j], min);
                        }
                        result += (right - left - 1) * min - rangeSum;
                        left = i;
                    } else {
                        left = i;
                    }
                    continue;
                }
                if (i == len - 1 && height[i - 1] <= height[i]) {
                    if (left != -1 && right <= left) {
                        right = i;
                        int j = right + 1;
                        if (height[left] >= height[right]) {
                            for (; j < len; j++) {
                                if (height[j] >= height[right]) {
                                    right = j;
                                }
                                if (height[j] >= height[left]) {
                                    break;
                                }
                            }
                        }
                        rangeSum = 0;
                        int min = Math.min(height[left], height[right]);
                        for (j = left + 1; j < right; j++) {
                            rangeSum += Math.min(height[j], min);
                        }
                        result += (right - left - 1) * min - rangeSum;
                        left = i + 1;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Trap trap = new Trap();
        Assert.assertEquals(2, trap.trap(new int[] {5,3,7,7}));
        Assert.assertEquals(9, trap.trap(new int[] {5,2,0,3,2,4}));
        Assert.assertEquals(83, trap.trap(new int[] {6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3}));
        Assert.assertEquals(9, trap.trap(new int[] {4,2,0,5,2,5}));
        Assert.assertEquals(10, trap.trap(new int[] {5,2,0,5,2,4}));
        Assert.assertEquals(9, trap.trap(new int[] {4,2,0,3,2,5}));
        Assert.assertEquals(14, trap.trap(new int[] {5,2,1,2,1,5}));
        Assert.assertEquals(4, trap.trap(new int[] {5,4,6,2,5}));
        Assert.assertEquals(8, trap.trap(new int[] {5,4,1,2,5}));
        Assert.assertEquals(1, trap.trap(new int[] {5,4,1,2}));
        Assert.assertEquals(6, trap.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
        Assert.assertEquals(6, trap.trap(new int[] {1,1,0,2,1,0,1,3,2,1,2,1}));
        Assert.assertEquals(4, trap.trap(new int[] {6,2,9}));
        Assert.assertEquals(0, trap.trap(new int[] {6,10,9}));
        Assert.assertEquals(0, trap.trap(new int[] {6,8,9}));
        Assert.assertEquals(0, trap.trap(new int[] {10,8,7}));
    }
}
