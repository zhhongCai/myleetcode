package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 1081
 * @Author: theonecai
 * @Date: Create in 2020/10/25 17:28
 * @Description:
 */
public class SmallestSubsequence {
    public String smallestSubsequence(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.contains(ch)) {
                continue;
            }
            while (!stack.isEmpty() && ch < stack.peek() && s.indexOf(stack.peek(), i) != -1) {
                stack.pop();
            }
            stack.push(ch);
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SmallestSubsequence smallestSubsequence = new SmallestSubsequence();
        Assert.assertEquals("bca", smallestSubsequence.smallestSubsequence("bcbcbcababa"));
        Assert.assertEquals("abc", smallestSubsequence.smallestSubsequence("cbaacabcaaccaacababa"));
        Assert.assertEquals("adbc", smallestSubsequence.smallestSubsequence("cdadabcc"));
        Assert.assertEquals("abcd", smallestSubsequence.smallestSubsequence("abcd"));
        Assert.assertEquals("eacb", smallestSubsequence.smallestSubsequence("ecbacba"));
        Assert.assertEquals("letcod", smallestSubsequence.smallestSubsequence("leetcode"));
    }
}
