package com.theonecai.leetcode.math;

import org.junit.Assert;

/**
 * leetcode 9
 * @Author: theonecai
 * @Date: Create in 2021/3/21 20:57
 * @Description:
 */
public class IsPalindrome {
    public boolean isPalindrome(int x) {
        String a = String.valueOf(x);
        return isPalindrome(a);
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        Assert.assertTrue(isPalindrome.isPalindrome(123321));
    }
}
