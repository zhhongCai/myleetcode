package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/26 19:41
 * @Description:
 */
public class PalindromeSubSequence {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len;
        int lenEven;
        String maxLengthStr = "";
        for (int i = 0; i < chars.length; i++) {
            len = subsequence(chars, i - 1, i + 1);
            lenEven = subsequence(chars, i, i + 1);
            if (len > maxLengthStr.length()) {
                maxLengthStr = String.valueOf(chars, i - len  / 2, len);
            }
            if (lenEven > maxLengthStr.length()) {
                maxLengthStr = String.valueOf(chars, i - lenEven  / 2 + 1, lenEven);
            }
        }

        return maxLengthStr;
    }

    private int subsequence(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length) {
            if (chars[left] != chars[right]) {
                break;
            }
            left--;
            right++;
        }

        return right - left - 1;
    }

    public static void main(String[] args) {
        PalindromeSubSequence  palindromeSubSequence = new PalindromeSubSequence();
        Assert.assertEquals("ababa", palindromeSubSequence.longestPalindrome("ababad"));
        Assert.assertEquals("a", palindromeSubSequence.longestPalindrome("abcd"));
        Assert.assertEquals("aaa", palindromeSubSequence.longestPalindrome("aaa"));
        Assert.assertEquals("a", palindromeSubSequence.longestPalindrome("a"));
        Assert.assertEquals("aa", palindromeSubSequence.longestPalindrome("aa"));
        Assert.assertEquals("aaaa", palindromeSubSequence.longestPalindrome("aaaa"));
        Assert.assertEquals("aaaaa", palindromeSubSequence.longestPalindrome("aaaaa"));
        Assert.assertEquals("bb", palindromeSubSequence.longestPalindrome("cbbd"));
        Assert.assertEquals("bb", palindromeSubSequence.longestPalindrome("cbb"));
        Assert.assertEquals("bb", palindromeSubSequence.longestPalindrome("bbd"));
        Assert.assertEquals("tattarrattat", palindromeSubSequence.longestPalindrome("tattarrattat"));
    }
}
