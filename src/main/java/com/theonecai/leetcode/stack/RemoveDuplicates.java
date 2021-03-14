package com.theonecai.leetcode.stack;

import org.junit.Assert;

/**
 * leetcode 1047
 * @Author: theoonecai
 * @Date: Create in 2021/3/9 09:05
 * @Description:
 */
public class RemoveDuplicates {

    public String removeDuplicates2(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] stack = new char[s.length()];
        int index = 0;
        stack[0] = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (index == -1) {
                stack[++index] = s.charAt(i);
                continue;
            }
            if (stack[index] == s.charAt(i)) {
                index--;
            } else {
                stack[++index] = s.charAt(i);
            }
        }

        if (index == -1) {
            return "";
        }
        return new String(stack, 0, index + 1);
    }

    public String removeDuplicates(String s) {
        char[] stack = s.toCharArray();
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            if (index != -1 && stack[index] == s.charAt(i)) {
                index--;
            } else {
                stack[++index] = s.charAt(i);
            }
        }

        if (index == -1) {
            return "";
        }
        return new String(stack, 0, index + 1);
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        Assert.assertEquals("a", removeDuplicates.removeDuplicates("aaa"));
        Assert.assertEquals("ab", removeDuplicates.removeDuplicates("aaab"));
        Assert.assertEquals("bab", removeDuplicates.removeDuplicates("baaab"));
        Assert.assertEquals("ca", removeDuplicates.removeDuplicates("abbaca"));
    }
}
