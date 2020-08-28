package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/26 19:41
 * @Description:
 */
public class PalindromeSubSequence {

    public String subsequence(String str) {
        /**
         * ababad
         *
         *
         */
        char[] chars = str.toCharArray();
        int length = 0;
        String maxLengthStr = String.valueOf(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            length = subSequence(chars, i);
            if (length > maxLengthStr.length()) {
                maxLengthStr = String.valueOf(chars, i - (length - 1) / 2, length);
            }
        }

        return maxLengthStr;
    }

    private int subSequence(char[] chars, int mid) {
        int left = mid - 1;
        int right = mid + 1;
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
        Assert.assertEquals("ababa", palindromeSubSequence.subsequence("ababad"));
        Assert.assertEquals("a", palindromeSubSequence.subsequence("abcd"));
        Assert.assertEquals("aaa", palindromeSubSequence.subsequence("aaa"));
        Assert.assertEquals("aaa", palindromeSubSequence.subsequence("aaaa"));
    }
}
