package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 190ß
 */
public class BinaryReversed {

    public int reverseBits(int n) {
        long result = 0;
        int shiftCount = 0;
        while (shiftCount < 32) {
            result <<= 1;
            if ((n & 1) == 1) {
                result |= 1;
            }
            n >>= 1;

            shiftCount++;
        }

        return (int)result;
    }

    public static void main(String[] args) {
        BinaryReversed binaryReversed = new BinaryReversed();
        Assert.assertEquals(-1,
                binaryReversed.reverseBits(Integer.valueOf("-111111111111111111111111111111", 2)));

        Assert.assertEquals(Integer.valueOf("0111111111111111111111111111111", 2).intValue(),
                binaryReversed.reverseBits(Integer.valueOf("1111111111111111111111111111110", 2)));

        Assert.assertEquals(Integer.valueOf("1111111111111111111111111111111", 2).intValue(),
                binaryReversed.reverseBits(Integer.valueOf("1111111111111111111111111111111", 2)));

        Assert.assertEquals(Integer.valueOf("0", 2).intValue(),
                binaryReversed.reverseBits(Integer.valueOf("0", 2)));

        Assert.assertEquals(Integer.valueOf("00000000000000000000000000000001", 2).intValue(),
                binaryReversed.reverseBits(Integer.valueOf("1000000000000000000000000000000", 2)));


    }
}
