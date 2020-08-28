package com.theonecai.leetcode.backtrace;

import org.junit.Assert;

/**
 * leetcode 44
 * @Author: theonecai
 * @Date: Create in 2020/7/3 22:33
 * @Description:
 */
public class PatternMatch {
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
            if (nextCharIsStar(patternIndex, pattern)) {
                rmatch(patternIndex + 2, textIndex, text, pattern);
            }
            return;
        }

        if (nextCharIsStar(patternIndex, pattern)) {
            rmatch(patternIndex + 2, textIndex, text, pattern);

            if (charMatched(patternIndex, textIndex, pattern, text)) {
                rmatch(patternIndex, textIndex + 1, text, pattern);
            }
        } else if (charMatched(patternIndex, textIndex, pattern, text)) {
            rmatch(patternIndex + 1, textIndex + 1, text, pattern);
        }
        mem[patternIndex][textIndex] = true;
    }

    private boolean nextCharIsStar(int patternIndex, String pattern) {
        return patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*';
    }

    private boolean charMatched(int patternIndex, int textIndex, String pattern, String text) {
        return pattern.charAt(patternIndex) == text.charAt(textIndex) || pattern.charAt(patternIndex) == '.';
    }

    public static void main(String[] args) {
        PatternMatch pm = new PatternMatch();
        String p = "absa*d";
        String s = "absaaad";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "abd";
        p = ".*";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "aa";
        p = "a";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatch(s, p));
        s = "aa";
        p = "a*";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "ab";
        p = ".*";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "aab";
        p = "c*a*b";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "mississippi";
        p = "mis*is*p*.";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatch(s, p));
        s = "a";
        p = ".";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "";
        p = ".";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatch(s, p));
        s = "";
        p = "a*";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "";
        p = "";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "ab";
        p = ".*c";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatch(s, p));
        s = "aaa";
        p = "ab*a*c*a";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "ab";
        p = "ab*";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "a";
        p = "ab*";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "bbbba";
        p = ".*a*a";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertTrue(pm.isMatch(s, p));
        s = "aaaa";
        p = "aaa";
        System.out.println("\"" + p+ "\" matched \"" + s +"\": " + pm.isMatch(s, p));
        Assert.assertFalse(pm.isMatch(s, p));
    }
}
