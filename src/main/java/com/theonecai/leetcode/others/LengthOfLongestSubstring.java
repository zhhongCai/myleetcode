package com.theonecai.leetcode.others;

import com.theonecai.algorithms.RandomStringUtil;

import java.util.Arrays;

/**
 * leetcode 3
 * @Author: theonecai
 * @Date: Create in 2020/6/29 22:16
 * @Description:
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] lastIndex = new int[257];
        Arrays.fill(lastIndex, -1);

        int max = 1;
        int len = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (lastIndex[(int)s.charAt(i)] == -1) {
                len++;
            } else {
                if (start < lastIndex[s.charAt(i)]) {
                    start = lastIndex[s.charAt(i)];
                }
                if (len > max) {
                    max = len;
                }

                len = i - start;
            }
            lastIndex[(int)s.charAt(i)] = i;

            if (len > max) {
                max = len;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        String a = "abcabcdabc";
        LengthOfLongestSubstring substring = new LengthOfLongestSubstring();
        System.out.println(substring.lengthOfLongestSubstring(a));
        System.out.println(substring.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(substring.lengthOfLongestSubstring("bbbbb"));
        System.out.println(substring.lengthOfLongestSubstring("pwwkew"));
        System.out.println(substring.lengthOfLongestSubstring("abcdefghikklmnopqabcdefjhiasdf"));
        System.out.println(substring.lengthOfLongestSubstring("dvdf"));
        System.out.println(substring.lengthOfLongestSubstring("abba"));
        System.out.println(substring.lengthOfLongestSubstring("abcccccba"));
        System.out.println(substring.lengthOfLongestSubstring("wobgrovw"));
        System.out.println(substring.lengthOfLongestSubstring("wobgrovacdefw"));

        for (int i = 0; i < 100; i++) {
            a = RandomStringUtil.randomString(20);
            System.out.println(a);
            System.out.println(substring.lengthOfLongestSubstring(a));
        }

    }
}
