package com.theonecai.leetcode.math;

import org.junit.Assert;

/**
 * leetcode 7
 * @Author: theonecai
 * @Date: Create in 2021/3/14 17:22
 * @Description:
 */
public class Reverse {

    public int reverse(int x) {
        return (int) reverse((long)x);
    }

    private long reverse(long x) {
        if (x < 0) {
            long a = reverse(-x);
            if (a == (long) Integer.MAX_VALUE + 1L) {
                return Integer.MIN_VALUE;
            }
            return (int)-a;
        }
        long res = 0;
        while (x > 0) {
            res *= 10;
            res += x % 10;
            x /= 10;
        }
        if (res > Integer.MAX_VALUE + 1L) {
            return 0;
        }

        return (int)res;
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        Assert.assertEquals(321, reverse.reverse(123));
        Assert.assertEquals(-321, reverse.reverse(-123));
        Assert.assertEquals(-21, reverse.reverse(-120));
        Assert.assertEquals(21, reverse.reverse(120));
        Assert.assertEquals(0, reverse.reverse(0));
    }
}
