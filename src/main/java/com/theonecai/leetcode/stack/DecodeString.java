package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 394
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/6 21:18
 * @Description:
 */
public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.trim().equals("")) {
            return s;
        }
        Stack<String> stack = new Stack<>();
        char ch;
        for (int i = s.length() - 1; i >= 0; i--) {
            ch = s.charAt(i);
            if (isAlpha(ch)) {
                String str = alphaStrPre(s, i);
                i -= str.length() - 1;
                stack.push(str);
            } else if (ch == ']') {
                stack.push("]");
            } else if (ch == '[') {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty()) {
                    String top = stack.pop();
                    if (top.equals("]")) {
                        break;
                    }
                    sb.append(top);
                }
                String numStr = numPre(s, i - 1);
                int num = Integer.parseInt(numStr);
                String tmp = sb.toString();
                while (num-- > 1) {
                    sb.append(tmp);
                }
                stack.push(sb.toString());
                i -= numStr.length();
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    private String alphaStrPre(String s, int end) {
        int i = end;
        while (i >= 0 && isAlpha(s.charAt(i))) {
            i--;
        }
        return s.substring(i + 1, end + 1);
    }

    private String numPre(String s, int end) {
        int i = end;
        while (i >= 0 && isDigital(s.charAt(i))) {
            i--;
        }
        return s.substring(i + 1, end + 1);
    }

    private boolean isAlpha(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    private boolean isDigital(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        Assert.assertEquals("abccabccabccbdd", decodeString.decodeString("3[ab2[c]]b2[d]"));
        Assert.assertEquals("aaa", decodeString.decodeString("3[a]"));
        Assert.assertEquals("a", decodeString.decodeString("a"));
        Assert.assertEquals("ab", decodeString.decodeString("ab"));
        Assert.assertEquals("abb", decodeString.decodeString("a2[b]"));
        Assert.assertEquals("babababa", decodeString.decodeString("4[ba]"));
    }
}
