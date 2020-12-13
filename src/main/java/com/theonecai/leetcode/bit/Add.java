package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 17.01
 * @Author: theonecai
 * @Date: Create in 2020/12/6 17:29
 * @Description:
 */
public class Add {
    public int add(int a, int b) {
        int carry = 0;
        while (b != 0) {
            carry = (a & b) << 1;
            a ^= b;
            b = carry;
        }

        return a;
    }

    public static void main(String[] args) {
        Add add = new Add();
        Assert.assertEquals(3, add.add(1, 2));
        Assert.assertEquals(23, add.add(11, 12));
        Assert.assertEquals(-1, add.add(11, -12));
    }
}
