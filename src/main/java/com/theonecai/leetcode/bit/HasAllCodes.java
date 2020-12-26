package com.theonecai.leetcode.bit;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 1461
 */
public class HasAllCodes {

    public boolean hasAllCodes(String s, int k) {
        boolean[] exists = new boolean[1 << k];
        int num = 0;
        for (int i = 0; i < s.length() - k + 1; i++) {
            if (i == 0) {
                for (int j = 0; j < k; j++) {
                    if (s.charAt(i + j) == '1') {
                        num |= 1 << (k - j - 1);
                    }
                }
            } else {
                num &= (1 << (k - 1)) - 1;
                num <<= 1;
                if (s.charAt(i + k - 1) == '1') {
                    num |= 1;
                }
            }
            exists[num] = true;
        }
        for (int i = 0; i < exists.length; i++) {
            if (!exists[i]) {
                return false;
            }
        }

        return true;
    }

    public boolean hasAllCodes2(String s, int k) {
        Set<Integer> exists = new HashSet<>(1 << k);
        int num = 0;
        for (int i = 0; i < s.length() - k + 1; i++) {
            if (i == 0) {
                for (int j = 0; j < k; j++) {
                    if (s.charAt(i + j) == '1') {
                        num |= 1 << (k - j - 1);
                    }
                }
            } else {
                num &= (1 << (k - 1)) - 1;
                num <<= 1;
                if (s.charAt(i + k - 1) == '1') {
                    num |= 1;
                }
            }
            exists.add(num);
        }

        return exists.size() == (1 << k);
    }

    public static void main(String[] args) {
        HasAllCodes hasAllCodes = new HasAllCodes();
        Assert.assertTrue(hasAllCodes.hasAllCodes("000000000000000000100110101111000", 4));
        Assert.assertTrue(hasAllCodes.hasAllCodes("01100", 2));
        Assert.assertTrue(hasAllCodes.hasAllCodes("00000000001011100", 3));
        Assert.assertTrue(hasAllCodes.hasAllCodes("00110110", 2));
        Assert.assertTrue(hasAllCodes.hasAllCodes("00110", 2));
        Assert.assertTrue(hasAllCodes.hasAllCodes("0110", 1));
        Assert.assertFalse(hasAllCodes.hasAllCodes("0110", 2));
        Assert.assertFalse(hasAllCodes.hasAllCodes("0000000001011100", 4));
    }
}
