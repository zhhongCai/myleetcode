package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 1734
 * @Author: theonecai
 * @Date: Create in 5/11/21 22:36
 * @Description:
 */
public class Decode {
    public int[] decode(int[] encoded) {
        int[] nums = new int[encoded.length + 1];
        int first = 0;
        for (int i = 1, j = 1; i <= nums.length; i++, j += 2) {
            first ^= i;
            if (j < encoded.length) {
                first ^= encoded[j];
            }
        }
        nums[0] = first;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] ^ encoded[i - 1];
        }

        return nums;
    }

    public static void main(String[] args) {
        Decode decode = new Decode();
        Assert.assertArrayEquals(new int[]{1,2,3}, decode.decode(new int[]{3,1}));
        Assert.assertArrayEquals(new int[]{2,4,1,5,3}, decode.decode(new int[]{6,5,4,6}));
    }
}
