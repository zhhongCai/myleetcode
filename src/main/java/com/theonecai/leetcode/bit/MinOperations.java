package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 1558
 * @Author: theonecai
 * @Date: Create in 2020/10/12 21:44
 * @Description:
 */
public class MinOperations {

    public int minOperations(int[] nums) {
        int plusOneCount = 0;
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            plusOneCount += Integer.bitCount(num);
        }
        return plusOneCount + Integer.toBinaryString(max).length() - 1;
    }


    public int minOperations2(int[] nums) {
        int plusOneCount = 0;
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            while (num > 0) {
                int r = num & 1;
                if (r == 0) {
                    num = num >> 1;
                }  else {
                    plusOneCount++;
                    num--;
                }
            }
        }
        return plusOneCount + multiCount(max);
    }

    private int multiCount(int max) {
        int count = 0;
        while (max > 0) {
            if (max % 2 == 0) {
                count++;
                max /= 2;
            } else {
                max--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        /**
         * 0001
         * 0101
         */
        MinOperations minOperations = new MinOperations();
        Assert.assertEquals(5, minOperations.minOperations(new int[]{1, 5}));
        Assert.assertEquals(9, minOperations.minOperations(new int[]{1, 30}));
        Assert.assertEquals(3, minOperations.minOperations(new int[]{2, 2}));
        Assert.assertEquals(6, minOperations.minOperations(new int[]{4, 2, 5}));
        Assert.assertEquals(7, minOperations.minOperations(new int[]{3, 2, 2, 4}));
        Assert.assertEquals(8, minOperations.minOperations(new int[]{2, 4, 8, 16}));
    }
}
