package com.theonecai.leetcode.backtrace;

import org.junit.Assert;

/**
 * leetcode 10
 * @Author: theonecai
 * @Date: Create in 2020/7/3 22:33
 * @Description:
 */
public class PatternMatch2 {
    private boolean matched = false;

    private boolean[][] mem;

    public boolean isMatch(String s, String p) {
        mem = new boolean[p.length() + 1][s.length() + 1];
        matched = false;
        rmatch(0, 0, s, p);
        return matched;
    }

    private void rmatch(int patternIndex, int textIndex, String text, String pattern) {
        if (matched) {
            return;
        }
        if (mem[patternIndex][textIndex]) {
            return;
        }
        if (patternIndex >= pattern.length() && textIndex >= text.length()) {
            matched = true;
            mem[patternIndex][textIndex] = true;
            return;
        }
        System.out.println("f(" + patternIndex + "," + textIndex +")");

        if (patternIndex >= pattern.length()) {
            return;
        }
        if (textIndex >= text.length()) {
            if (pattern.charAt(patternIndex) == '*') {
                rmatch(patternIndex + 1, textIndex, text, pattern);
            }
            return;
        }
        if (pattern.charAt(patternIndex) == '*') {
            for (int k = 0; k <= text.length() - textIndex; k++) {
                rmatch(patternIndex + 1, textIndex + k, text, pattern);
            }
        } else if (pattern.charAt(patternIndex) == '?' || pattern.charAt(patternIndex) == text.charAt(textIndex)) {
            rmatch(patternIndex + 1, textIndex + 1, text, pattern);
        }

        mem[patternIndex][textIndex] = true;
    }

    /**
     * state(pIdx][tIdx): 表示模式串[0~pIdex) 跟 字符串[0~tIdx)是否匹配
     * 1. pattern[pIdex-1] == text[tIdx-1] || pattern[pIdx - 1] == '?' :
     *      state[pIdx][tIdx] = state[pIdx - 1][tIdx - 1];
     * 2. pattern[pIdx] == '*':
     *      state[pIdx][tIdx] = state[pIdx][tIdx - 1] || state[pIdx - 1][tIdx]
     *
     *      state[pIdx][tIdx - 1]表示'*"代表1个或多个字符
     *      state[pIdx - 1][tIdx]表示'*"代表0个字符
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchDp(String s, String p) {
        boolean[][] state = new boolean[p.length() + 1][s.length() + 1];
        state[0][0] =true;

        for (int i = 1; i <= p.length(); i++) {
            state[i][0] = state[i - 1][0] && p.charAt(i - 1) == '*';
        }

        for (int tIdx = 1; tIdx <= s.length(); tIdx++) {
            for (int pIdx = 1; pIdx <= p.length(); pIdx++) {
                if (p.charAt(pIdx - 1) == s.charAt(tIdx - 1) || p.charAt(pIdx - 1) == '?') {
                    state[pIdx][tIdx] = state[pIdx - 1][tIdx - 1];
                } else if (p.charAt(pIdx - 1) == '*') {
                    state[pIdx][tIdx] = state[pIdx][tIdx - 1] || state[pIdx - 1][tIdx];
                }
            }
        }

        printState(state);

        return state[p.length()][s.length()];
    }

    private void printState(boolean[][] state) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                if (state[i][j]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PatternMatch2 pm = new PatternMatch2();
        String p = "aaa";
        String s = "a";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatchDp(s, p));
        s = "aa";
        p = "*";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatchDp(s, p));
        s = "cb";
        p = "?a";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatchDp(s, p));
        s = "adceb";
        p = "a*b*";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatchDp(s, p));
        s = "acdcb";
        p = "a*c?b";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatchDp(s, p));
        s = "";
        p = "";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatchDp(s, p));
        s = "";
        p = "*";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatchDp(s, p));
        s = "ab";
        p = "?*";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatchDp(s, p));

    }
}
