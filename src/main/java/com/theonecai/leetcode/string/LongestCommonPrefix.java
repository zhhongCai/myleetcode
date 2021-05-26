package com.theonecai.leetcode.string;

import org.junit.Assert;

/**
 * leetcode 14
 * @Author: theonecai
 * @Date: Create in 5/26/21 20:46
 * @Description:
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        int i = 0;
        while (i < 201) {
            boolean eq = true;
            for (String str : strs) {
                if (i >= str.length() || strs[0].charAt(i) != str.charAt(i)) {
                    eq = false;
                    i -= 1;
                    break;
                }
            }
            if (!eq) {
                break;
            }
            i++;
        }

        return i == -1 ? "" : strs[0].substring(0, i + 1);
    }

    public static void main(String[] args) {
        LongestCommonPrefix prefix = new LongestCommonPrefix();
        Assert.assertEquals("fl", prefix.longestCommonPrefix(new String[]{
                "flower","flow","flight"
        }));
        Assert.assertEquals("", prefix.longestCommonPrefix(new String[]{
                "dog","racecar","car"
        }));
        Assert.assertEquals("", prefix.longestCommonPrefix(new String[]{
                "dog","","cdr"
        }));
    }
}
