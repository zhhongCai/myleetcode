package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 1190
 * @Author: theonecai
 * @Date: Create in 5/26/21 20:15
 * @Description:
 */
public class ReverseParentheses {

    public String reverseParentheses(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ('(' == ch) {
                stack.push(sb.toString());
                sb.delete(0, sb.length());
            } else if (')' == ch) {
                if (stack.isEmpty()) {
                    stack.push(sb.reverse().toString());
                    sb.delete(0, sb.length());
                } else {
                    String tmp = stack.pop() + sb.reverse().toString();
                    sb.delete(0, sb.length());
                    sb.append(tmp);
                }
            } else {
                sb.append(ch);
            }
        }

        return stack.isEmpty() ? sb.toString() : stack.pop();
    }

    public static void main(String[] args) {
        ReverseParentheses reverseParentheses = new ReverseParentheses();
        Assert.assertEquals("suteawjeg", reverseParentheses.reverseParentheses("s()uteawj((eg))"));
        Assert.assertEquals("", reverseParentheses.reverseParentheses("()"));
        Assert.assertEquals("aa", reverseParentheses.reverseParentheses("aa"));
        Assert.assertEquals("iloveu", reverseParentheses.reverseParentheses("(u(love)i)"));
        Assert.assertEquals("dcba", reverseParentheses.reverseParentheses("(abcd)"));
        Assert.assertEquals("leetcode", reverseParentheses.reverseParentheses("(ed(et(oc))el)"));
        Assert.assertEquals("apmnolkjihgfedcbq", reverseParentheses.reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }
}
