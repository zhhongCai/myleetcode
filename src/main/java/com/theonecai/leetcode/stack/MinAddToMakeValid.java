package com.theonecai.leetcode.stack;

/**
 * leetcode 921
 * @Author: theonecai
 * @Date: Create in 2020/6/20 21:35
 * @Description:
 */
public class MinAddToMakeValid {
    private char[] stack;

    private int current;

    public int minAddToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();
        this.current = -1;
        this.stack = new char[s.length()];

        stack[++this.current] = s.charAt(0);
        for (int i = 1; i < len; i++) {
            char n = s.charAt(i);
            if (this.current >= 0) {
                char c = stack[this.current];
                if (c == '(' && n == ')') {
                    --this.current;
                    continue;
                }
            }
            stack[++this.current] = n;
        }

        if (this.current == -1) {
            return 0;
        }
        return this.current + 1;
    }

    public static void main(String[] args) {
        MinAddToMakeValid m = new MinAddToMakeValid();
        String a = "())";
        System.out.println(a + " : " + m.minAddToMakeValid(a));
        a = "(((";
        System.out.println(a + " : " + m.minAddToMakeValid(a));
        a = "()";
        System.out.println(a + " : " + m.minAddToMakeValid(a));
        a = "()))((";
        System.out.println(a + " : " + m.minAddToMakeValid(a));
    }

}
