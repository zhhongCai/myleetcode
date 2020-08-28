package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 316
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/30 21:22
 * @Description:
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        int[] charCount = new int[26];
        boolean notDuplicate = true;
        for (char c : s.toCharArray()) {
            charCount[c - 'a'] += 1;
            if (charCount[c - 'a'] > 1) {
                notDuplicate = false;
            }
        }
        if (notDuplicate) {
            return s;
        }

        char currentCh;
        Stack<Character> incStack = new Stack<>();
        incStack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            currentCh = s.charAt(i);
            if (incStack.contains(currentCh)) {
                charCount[currentCh - 'a'] -= 1;
                continue;
            }
            while (!incStack.isEmpty() && currentCh <= incStack.peek() && charCount[incStack.peek() - 'a'] > 1) {
                char pre = incStack.peek();
                incStack.pop();
                charCount[pre - 'a'] -= 1;
            }
            incStack.push(currentCh);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < incStack.size(); i++) {
            sb.append(incStack.elementAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters removeDuplicateLetters = new RemoveDuplicateLetters();
        Assert.assertEquals("ilrhjfyzmnstwkboxuc", removeDuplicateLetters.removeDuplicateLetters("mitnlruhznjfyzmtmfnstsxwktxlboxutbic"));
        Assert.assertEquals("abcd", removeDuplicateLetters.removeDuplicateLetters("abdabcd"));
        Assert.assertEquals("abcde", removeDuplicateLetters.removeDuplicateLetters("abcdabced"));
        Assert.assertEquals("acdb", removeDuplicateLetters.removeDuplicateLetters("cbacdcbc"));
        Assert.assertEquals("abc", removeDuplicateLetters.removeDuplicateLetters("abacbc"));
        Assert.assertEquals("bac", removeDuplicateLetters.removeDuplicateLetters("cbac"));
        Assert.assertEquals("ba", removeDuplicateLetters.removeDuplicateLetters("baa"));
        Assert.assertEquals("a", removeDuplicateLetters.removeDuplicateLetters("aa"));
        Assert.assertEquals("ab", removeDuplicateLetters.removeDuplicateLetters("baba"));
        Assert.assertEquals("abc", removeDuplicateLetters.removeDuplicateLetters("aabbcc"));
        Assert.assertEquals("acb", removeDuplicateLetters.removeDuplicateLetters("bacab"));
        Assert.assertEquals("dacb", removeDuplicateLetters.removeDuplicateLetters("dbacab"));
    }
}
