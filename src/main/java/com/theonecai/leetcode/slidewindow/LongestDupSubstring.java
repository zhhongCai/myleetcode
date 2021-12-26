package com.theonecai.leetcode.slidewindow;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 1044
 */
public class LongestDupSubstring {
    public String longestDupSubstring(String s) {
        int n = s.length();
        String ans = "";
        int left = 1;
        int right = n - 1;
        while (left <= right) {
            int len = left + (right - left) / 2;
            String a = check(s, len);
            if (a.length() == len) {
                left = len + 1;
            } else {
                right = len - 1;
            }
            if (a.length() > ans.length()) {
                ans = a;
            }
        }

        return ans;
    }

    private String check(String s, int len) {
        int n = s.length();
        long prime = 31L;
        long power = 1L;
        long hash = 0L;
        for (int i = 0; i < len; i++) {
            hash = hash * prime + (s.charAt(i) - 'a');
            power *= prime;
        }
        Set<Long> exists = new HashSet<>();
        exists.add(hash);
        for (int i = len; i < n; i++) {
            // left shift and minus head add tail
            hash = hash * prime - power * (s.charAt(i - len) - 'a') + (s.charAt(i) - 'a');
            if (exists.contains(hash)) {
                return s.substring(i - len + 1, i + 1);
            }
            exists.add(hash);
        }
        return "";
    }

    public static void main(String[] args) {
        LongestDupSubstring longestDupSubstring = new LongestDupSubstring();
        Assert.assertEquals("a", longestDupSubstring.longestDupSubstring("aa"));
        Assert.assertEquals("ana", longestDupSubstring.longestDupSubstring("banana"));
    }
}
