package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 330
 * @Author: theonecai
 * @Date: Create in 2020/9/18 20:43
 * @Description:
 */
public class MinPatches {

    public int minPatches(int[] nums, int n) {
        long miss = 1;
        int count = 0;
        int i = 0;
        while (miss <= n) {
            if (nums != null && i < nums.length && nums[i] <= miss) {
                miss += nums[i];
                i++;
            } else {
                miss += miss;
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        MinPatches minPatches = new MinPatches();
        Assert.assertEquals(29, minPatches.minPatches(new int[]{1,2,31,33}, 2147483647));
        Assert.assertEquals(3, minPatches.minPatches(null, 5));
        Assert.assertEquals(2, minPatches.minPatches(new int[]{1,2, 2}, 20));
        Assert.assertEquals(1, minPatches.minPatches(new int[]{1,3,5}, 6));
        /**
         * 00001
         * 00101
         * 01010
         * 11010
         */
        Assert.assertEquals(2, minPatches.minPatches(new int[]{1,5, 10}, 20));
        Assert.assertEquals(0, minPatches.minPatches(new int[]{1,2, 2}, 5));
    }
}
