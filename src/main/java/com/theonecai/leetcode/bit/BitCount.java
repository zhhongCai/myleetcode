package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 191
 */
public class BitCount {

    public int hammingWeight2(int num) {
        int count = 0;
        while (num != 0) {
            if ((num & 1) != 0) {
                count++;
            }
            num >>= 1;
        }
        return count;
    }

    public int hammingWeight(int num) {
//        return Integer.bitCount(num);
        int count = 0;
        while (num != 0) {
            count++;
            num &= (num - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        BitCount bitCount = new BitCount();
        Assert.assertEquals(bitCount.hammingWeight(123), bitCount.hammingWeight2(123));
    }
}
