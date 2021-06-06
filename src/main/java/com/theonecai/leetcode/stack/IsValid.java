package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 20
 * @Author: theonecai
 * @Date: Create in 6/5/21 21:36
 * @Description:
 */
public class IsValid {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ('(' == ch || '[' == ch || '{' == ch) {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char c = stack.pop();
                if (('(' != c && ch == ')') || ('[' != c && ch == ']') || ('{' != c && ch == '}')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        IsValid isValid = new IsValid();
        Assert.assertTrue(isValid.isValid("{}()[]"));
        Assert.assertFalse(isValid.isValid("{})[]"));
        Assert.assertTrue(isValid.isValid("[{}()[]]"));
        Assert.assertTrue(isValid.isValid("[{()[]()[]}()[]]"));
    }
}
