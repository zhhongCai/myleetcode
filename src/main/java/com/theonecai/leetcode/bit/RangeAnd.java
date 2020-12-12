package com.theonecai.leetcode.bit;

import org.junit.Assert;

public class RangeAnd {

    public int rangeAnd(int m, int n) {
        if (m == n) {
            return m;
        }
        int shift = 0;
        while (m < n) {
            m >>= 1;
            n >>= 1;
            shift++;
        }

        return m << shift;
    }

    public static void main(String[] args) {
        RangeAnd rangeAnd = new RangeAnd();
        Assert.assertEquals(5, rangeAnd.rangeAnd(5, 5));
        Assert.assertEquals(6, rangeAnd.rangeAnd(6, 7));
    }
}
