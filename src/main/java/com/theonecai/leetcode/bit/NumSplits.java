package com.theonecai.leetcode.bit;

/**
 * @Author: theonecai
 * @Date: Create in 2020/12/27 10:22
 * @Description:
 */
public class NumSplits {
    public int numSplits(String s) {
        if (s.length() < 2) {
            return 0;
        }
        int allMask = 0;
        int[] counts = new int[26];
        int n;
        for (int i = 0; i < s.length(); i++) {
            n = s.charAt(i) - 'a';
            allMask |= 1 << n;
            counts[n]++;
        }


        int total = charNum(counts);
        int leftMask = 0;
        int count = 0;
        int[] preCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            n = s.charAt(i) - 'a';
            counts[n]--;
            preCount[n]++;
            if (charNum(preCount) == charNum(counts)) {
                count++;
            }
        }

        return count;
    }

    private int charNum(int[] counts) {
        int total = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                total++;
            }
        }
        return total;
    }
}
