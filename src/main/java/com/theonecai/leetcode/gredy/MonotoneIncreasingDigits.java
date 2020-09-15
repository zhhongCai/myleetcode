package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 738
 * @Author: theonecai
 * @Date: Create in 2020/9/14 19:28
 * @Description:
 */
public class MonotoneIncreasingDigits {

    public int monotoneIncreasingDigits(int N) {
        String num = String.valueOf(N);
        if (num.length() == 1) {
            return N;
        }
        char[] chars = num.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (i - 1 >= 0) {
                if (chars[i] < chars[i - 1]) {
                    chars[i] = '9';
                    chars[i - 1] = chars[i - 1] - 1 < '0' ? '0' : (char)(chars[i - 1] - 1);
                    int j = i + 1;
                    while (j < chars.length) {
                        chars[j++] = '9';
                    }
                }
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    public static void main(String[] args) {
        MonotoneIncreasingDigits digits = new MonotoneIncreasingDigits();
        Assert.assertEquals(299, digits.monotoneIncreasingDigits(321));
        Assert.assertEquals(299, digits.monotoneIncreasingDigits(324));
        Assert.assertEquals(99, digits.monotoneIncreasingDigits(100));
        Assert.assertEquals(789, digits.monotoneIncreasingDigits(789));
        Assert.assertEquals(111, digits.monotoneIncreasingDigits(111));
        Assert.assertEquals(24999, digits.monotoneIncreasingDigits(25467));
        Assert.assertEquals(99999, digits.monotoneIncreasingDigits(102489));
        Assert.assertEquals(299, digits.monotoneIncreasingDigits(322));
        Assert.assertEquals(99999, digits.monotoneIncreasingDigits(101089));
        Assert.assertEquals(299999, digits.monotoneIncreasingDigits(333222));
    }
}
