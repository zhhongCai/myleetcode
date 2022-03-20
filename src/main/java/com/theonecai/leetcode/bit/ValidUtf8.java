package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 393
 */
public class ValidUtf8 {

    public boolean validUtf8(int[] data) {
        int n = data.length;
        for (int i = 0; i < n; i++) {
            int d = data[i];
            int c = 0;
            for (int j = 7; j > 0; j--) {
                if (((d >> j) & 1) == 1) {
                    c++;
                } else {
                    break;
                }
            }
            if (c > 4 || c == 1) {
                return false;
            }
            if (c == 0) {
                continue;
            }
            c -= 1;
            if (i + c >= n) {
                return false;
            }
            for (int j = 0; j < c; j++) {
                d = data[++i];
                if ((d >> 6) != 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidUtf8 validUtf8 = new ValidUtf8();
        Assert.assertFalse(validUtf8.validUtf8(new int[]{240,162,138,147,145}));
        Assert.assertFalse(validUtf8.validUtf8(new int[]{145}));
        Assert.assertTrue(validUtf8.validUtf8(new int[]{197,130,1}));
        Assert.assertFalse(validUtf8.validUtf8(new int[]{235,140,4}));
    }
}
