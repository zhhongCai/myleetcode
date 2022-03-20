package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class WeekendD68 {

    public int mostWordsFound(String[] sentences) {
        int ans = 0;
        for (String sentence : sentences) {
            int c = 0;
            for (int i = 0; i < sentence.length(); i++) {
                if (sentence.charAt(i) == ' ') {
                    c++;
                }
            }
            ans = Math.max(ans, c + 1);
        }
        return ans;
    }

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> spMap = new HashSet<>();
        Set<String> ans = new HashSet<>();
        for (String supply : supplies) {
            spMap.add(supply);
        }

        int n = recipes.length;
        boolean[] ok = new boolean[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (!ok[i]) {
                    boolean flag = true;
                    for (String ingredient : ingredients.get(i)) {
                        if (!spMap.contains(ingredient)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        ans.add(recipes[i]);
                        spMap.add(recipes[i]);
                        ok[i] = true;
                    }
                }
            }
        }

        return new ArrayList<>(ans);
    }

   public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        char[] str = s.toCharArray();
        char[] lock = locked.toCharArray();
        boolean allZero = true;
        for (int i = 0; i < n; i++) {
            if (allZero && lock[i] == '1') {
                allZero = false;
            }
            if ('(' == str[i]) {
                if (!stack.isEmpty()) {
                    if (str[stack.peek()] == ')' && lock[i] == '0' & lock[stack.peek()] == '0') {
                        stack.pop();
                        continue;
                    }
                }
                stack.push(i);
            } else {
                // ')'

                if (!stack.isEmpty()) {
                    if (str[stack.peek()] == '(') {
                        stack.pop();
                        continue;
                    }
                    if (lock[stack.peek()] == '0') {
                        stack.pop();
                        continue;
                    }
                }
                stack.push(i);
            }
        }
        if (stack.size() == n) {
            return false;
        }
        if (stack.size() == 0 || allZero) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder ls = new StringBuilder();
        for (Integer i : stack) {
           sb.append(str[i]);
           ls.append(lock[i]);
        }


       return canBeValid(sb.toString(), ls.toString());
    }

    public static void main(String[] args) {
        WeekendD68 weekend = new WeekendD68();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {

    }

    private void test3() {

        Assert.assertTrue(canBeValid("((()(()()))()((()()))))()((()(()", "10111100100101001110100010001001"));
        Assert.assertTrue(canBeValid("))", "00"));
        Assert.assertTrue(canBeValid(")((())))", "01111110"));
        Assert.assertTrue(canBeValid("(()))())((", "0000000000"));
        Assert.assertTrue(canBeValid("(())", "1111"));
        Assert.assertFalse(canBeValid(")(", "10"));
        Assert.assertTrue(canBeValid(")(", "00"));
        Assert.assertTrue(canBeValid("))()))", "010100"));
        Assert.assertTrue(canBeValid("()()", "010100"));
        Assert.assertFalse(canBeValid("))())", "01000"));

    }

    private void test2() {
    }

    private void test() {

    }
}
