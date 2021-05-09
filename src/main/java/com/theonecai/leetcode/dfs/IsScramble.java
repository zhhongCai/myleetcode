package com.theonecai.leetcode.dfs;

import org.junit.Assert;

/**
 * 87
 * @Author: theonecai
 * @Date: Create in 4/28/21 20:49
 * @Description:
 */
public class IsScramble {

    private boolean[][][] memo;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        // 长度不一致
        if (n != m) {
            return false;
        }
        if (memo == null) {
            memo = new boolean[n][n][n];
        }

        // 相同
        if (s1.equals(s2)) {
            return true;
        }

        // 含相同个数的字符
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }

        for (int i = 1; i < n; i++) {
            boolean res = (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i)))
                    || (isScramble(s1.substring(0, i), s2.substring(n - i)) && isScramble(s1.substring(i), s2.substring(0, n - i)));
            if (res) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        IsScramble isScramble = new IsScramble();
        Assert.assertTrue(isScramble.isScramble("eebaacbcbcadaaedceaaacadccd", "eadcaacabaddaceacbceaabeccd"));
        Assert.assertTrue(isScramble.isScramble("abb", "bba"));
        Assert.assertTrue(isScramble.isScramble("great", "rgeat"));
        Assert.assertTrue(isScramble.isScramble("abc", "abc"));
        Assert.assertFalse(isScramble.isScramble("abcde", "caebd"));
    }
}
