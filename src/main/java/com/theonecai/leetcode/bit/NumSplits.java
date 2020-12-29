package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * 1525
 */
public class NumSplits {

    public int numSplits(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int len = s.length();
        int[] leftToRightCount = new int[len];
        int[] rightToLeftCount = new int[len];
        boolean[] exists = new boolean[26];
        boolean[] exists2 = new boolean[26];
        leftToRightCount[0] = 1;
        rightToLeftCount[len - 1] = 1;
        exists[s.charAt(0) - 'a'] = true;
        exists2[s.charAt(len - 1) - 'a'] = true;
        char ch;
        char ch2;
        for (int i = 1, j = len - 2; i < len; i++, j--) {
            ch = s.charAt(i);
            ch2 = s.charAt(j);
            leftToRightCount[i] = exists[ch - 'a'] ? leftToRightCount[i - 1] : (leftToRightCount[i - 1] + 1);
            rightToLeftCount[j] = exists2[ch2 - 'a'] ? rightToLeftCount[j + 1] : (rightToLeftCount[j + 1] + 1);
            exists[ch - 'a'] = true;
            exists2[ch2 - 'a'] = true;
        }
        int count = 0;
        for (int i = 0; i < leftToRightCount.length - 1; i++) {
            if (leftToRightCount[i] == rightToLeftCount[i + 1]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumSplits splits = new NumSplits();
        Assert.assertEquals(6, splits.numSplits("ababababa"));
        Assert.assertEquals(0, splits.numSplits("a"));
        Assert.assertEquals(1, splits.numSplits("ab"));
        Assert.assertEquals(3, splits.numSplits("aaaa"));
    }
}
