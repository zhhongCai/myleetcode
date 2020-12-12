package com.theonecai.leetcode.bit;

import org.junit.Assert;

public class BinaryReversed {

    public int reversed(int num) {
        System.out.println(num + ": " + Integer.toBinaryString(num));
        long n = 0;
        int shiftCount = 0;
        int last = num < 0 ? 1 : 0;
        while (shiftCount < 31) {
            n <<= 1;
            if ((num & 1) == 1) {
                n |= 1;
            }
            num >>= 1;

            shiftCount++;
        }
        if (last == 1) {
            n >>= 1;
            n |= 1;
        }

        System.out.println(n + ": " + Long.toBinaryString(n));
        System.out.println();

        return (int)n;
    }

    public static void main(String[] args) {
        BinaryReversed binaryReversed = new BinaryReversed();
        Assert.assertEquals(-1,
                binaryReversed.reversed(Integer.valueOf("-111111111111111111111111111111", 2)));

        Assert.assertEquals(Integer.valueOf("0111111111111111111111111111111", 2).intValue(),
                binaryReversed.reversed(Integer.valueOf("1111111111111111111111111111110", 2)));

        Assert.assertEquals(Integer.valueOf("1111111111111111111111111111111", 2).intValue(),
                binaryReversed.reversed(Integer.valueOf("1111111111111111111111111111111", 2)));

        Assert.assertEquals(Integer.valueOf("0", 2).intValue(),
                binaryReversed.reversed(Integer.valueOf("0", 2)));

        Assert.assertEquals(Integer.valueOf("00000000000000000000000000000001", 2).intValue(),
                binaryReversed.reversed(Integer.valueOf("1000000000000000000000000000000", 2)));


    }
}
