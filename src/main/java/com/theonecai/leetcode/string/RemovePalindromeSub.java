package com.theonecai.leetcode.string;

/**
 * leetcode 1332
 */
public class RemovePalindromeSub {

    public int removePalindromeSub(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        if (left >= right) {
            return 1;
        }
        return 2;
    }

}
