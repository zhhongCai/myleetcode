package com.theonecai.leetcode.stack;

import org.junit.Assert;

/**
 * 334
 */
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n< 3) {
            return false;
        }
        int[] incStack = new int[4];
        int[] descStack = new int[4];
        int idx = 0;
        int index = 0;
        incStack[idx] = nums[0];
        descStack[index] = nums[n - 1];

        for (int i = 1, j = n - 2; i < n; i++, j--) {
            if (index >= 2) {
                return true;
            }
            while (index >= 0 && descStack[index] <= nums[j]) {
                index--;
            }
            descStack[++index] = nums[j];
            if (idx >= 2) {
                return true;
            }
            while (idx >= 0 && incStack[idx] >= nums[i]) {
                idx--;
            }
            incStack[++idx] = nums[i];
        }
        return idx >= 2 || index >= 2;
    }

    public static void main(String[] args) {
        IncreasingTriplet increasingTriplet = new IncreasingTriplet();
        Assert.assertTrue(increasingTriplet.increasingTriplet(new int[]{0,4,1,-1,2}));
        Assert.assertTrue(increasingTriplet.increasingTriplet(new int[]{1,2,1,3}));
        Assert.assertTrue(increasingTriplet.increasingTriplet(new int[]{20,100,10,12,5,13}));
        Assert.assertTrue(increasingTriplet.increasingTriplet(new int[]{1,1,1,4,5}));
        Assert.assertTrue(increasingTriplet.increasingTriplet(new int[]{1,2,3,4,5}));
        Assert.assertFalse(increasingTriplet.increasingTriplet(new int[]{5,4,3,2,1}));
        Assert.assertTrue(increasingTriplet.increasingTriplet(new int[]{2,1,5,0,4,6}));
    }
}
