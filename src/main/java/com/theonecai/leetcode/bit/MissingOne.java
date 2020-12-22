package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 268
 */
public class MissingOne {

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int bitmask = 0;
        for (int i = 0; i < nums.length; i++) {
            bitmask ^= nums[i];
            n ^= i;
        }
        return bitmask ^ n;
    }

    public static void main(String[] args) {
        MissingOne missingOne = new MissingOne();
        Assert.assertEquals(4, missingOne.missingNumber(new int[]{1,2,3,5,0}));
    }
}
