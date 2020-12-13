package com.theonecai.leetcode.bit;

import org.junit.Assert;

public class BitCount {

    public int bitCount2(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) != 0) {
                count++;
            }
            num >>= 1;
        }
        return count;
    }

    public int bitCount(int num) {
//        return Integer.bitCount(num);
        int count = 0;
        while (num > 0) {
            count++;
            num &= (num - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        BitCount bitCount = new BitCount();
        Assert.assertEquals(bitCount.bitCount(123), bitCount.bitCount2(123));
    }
}
