package com.theonecai.leetcode.math;

import org.junit.Assert;

/**
 *  leetcode 441
 */
public class ArrangeCoins {
    public int arrangeCoins(int n) {
//        n*(n-1) <= 2*x;
        long x = 2 * (long)n;
        x = (long)Math.sqrt((double) x);
        long y = x * (x + 1) / 2;

        if (y > n) {
            return (int)x - 1;
        }
        long r = n - y;
        if (r > x) {
            return (int)x + 1;
        }
        return (int)x;
    }

    public static void main(String[] args) {
        ArrangeCoins arrangeCoins = new ArrangeCoins();
        System.out.println(1571 * 1570/2 - 1234652);
        Assert.assertEquals(60070, arrangeCoins.arrangeCoins(1804289383));
        Assert.assertEquals(1570, arrangeCoins.arrangeCoins(1234652));
        Assert.assertEquals(14, arrangeCoins.arrangeCoins(111));
        Assert.assertEquals(2, arrangeCoins.arrangeCoins(5));
        Assert.assertEquals(3, arrangeCoins.arrangeCoins(8));
    }
}
