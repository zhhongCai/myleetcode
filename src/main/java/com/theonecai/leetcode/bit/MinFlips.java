package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 1318
 */
public class MinFlips {

    public int minFlips(int a, int b, int c) {
        if ((a | b) == c) {
            return 0;
        }
        int count = 0;
        int aBit;
        int bBit;
        int cBit;
        while (c != 0 || a != 0 || b != 0) {
            aBit = a & 1;
            bBit = b & 1;
            cBit = c & 1;
            if (cBit == 1 && aBit == 0 && bBit == 0) {
                count++;
            }
            if (cBit == 0 && (aBit | bBit) == 1) {
                count += aBit == 1 ? 1 : 0;
                count += bBit == 1 ? 1 : 0;
            }
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }

        return count;
    }

    public static void main(String[] args) {
        MinFlips minFlips = new MinFlips();
        Assert.assertEquals(3, minFlips.minFlips(8, 3, 5));
        Assert.assertEquals(3, minFlips.minFlips(2, 6, 5));
        Assert.assertEquals(1, minFlips.minFlips(4, 2, 7));
        Assert.assertEquals(0, minFlips.minFlips(1, 2, 3));
    }
}
