package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

/**
 * leetcode 125
 */
public class IsPalindrome {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            while (left < s.length() && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            if (left == s.length()) {
                return true;
            }
            while (right >= 0 && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        Assert.assertTrue(isPalindrome.isPalindrome(",."));
        Assert.assertTrue(isPalindrome.isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertFalse(isPalindrome.isPalindrome("race a car"));
    }
}
