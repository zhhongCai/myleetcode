package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 * 665
 * @Author: theonecai
 * @Date: Create in 2021/2/7 20:39
 * @Description:
 */
public class CheckPossibility {

    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        boolean canChanged = nums[0] <= nums[1];
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (canChanged) {
                    canChanged = false;
                    if (nums[i + 1] >= nums[i - 1]) {
                        nums[i] = nums[i + 1];
                    } else {
                        nums[i + 1] = nums[i];
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckPossibility checkPossibility = new CheckPossibility();
        Assert.assertTrue(checkPossibility.checkPossibility(new int[]{4,2,3}));
        Assert.assertFalse(checkPossibility.checkPossibility(new int[]{4,3,1}));
        Assert.assertFalse(checkPossibility.checkPossibility(new int[]{3,4,2,1}));
        Assert.assertFalse(checkPossibility.checkPossibility(new int[]{2,3,3,2,2}));
    }
}
