package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 693
 * @Author: theonecai
 * @Date: Create in 2020/12/6 20:47
 * @Description:
 */
public class HasAlternatingBits {

    public boolean hasAlternatingBits(int n) {
        int pre = -1;
        while (n != 0) {
            int tmp = n & 1;
            if (tmp == pre) {
                return false;
            }
            n = n >> 1;
            pre = tmp;
        }
        return true;
    }

    public static void main(String[] args) {
        HasAlternatingBits hasAlternatingBits = new HasAlternatingBits();
        Assert.assertTrue(hasAlternatingBits.hasAlternatingBits(5));
        Assert.assertFalse(hasAlternatingBits.hasAlternatingBits(7));
        Assert.assertFalse(hasAlternatingBits.hasAlternatingBits(11));
        Assert.assertTrue(hasAlternatingBits.hasAlternatingBits(10));
        Assert.assertFalse(hasAlternatingBits.hasAlternatingBits(3));
    }
}
