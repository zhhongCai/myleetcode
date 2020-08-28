package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 767
 *
 * @Author: zhenghong.cai
 * @Date: Create in 2020/8/6 16:53
 * @Description:
 */
public class ReorganizeString {

    public String reorganizeString2(String s) {
        if (s == null || s.length() < 3) {
            return s;
        }

        char[] sorted = s.toCharArray();
        Arrays.sort(sorted);

        char[] result = new char[sorted.length];
        char[] stack = new char[sorted.length];
        int resultIndex = -1;
        int top = -1;
        char ch;
        result[++resultIndex] = sorted[0];
        for (int i = 1; i < sorted.length; i++) {
            ch = sorted[i];
            if (top > -1 && stack[top] != result[resultIndex]) {
                result[++resultIndex] = stack[top--];
            }
            if (result[resultIndex] == ch) {
                stack[++top] = ch;
            } else {
                result[++resultIndex] = ch;
            }
        }

        int i = 0;
        while (top > -1) {
            if (stack[top] == result[resultIndex]) {
                if (result[i] == stack[top]) {
                    return "";
                }
                while (i < resultIndex && stack[top] == result[i]) {
                    i++;
                }
                if (i <= resultIndex) {
                    if (result[i] != stack[top]) {
                        if (i < resultIndex && result[i + 1] == stack[top]) {
                            if (top == 0){
                                return stack[top] + String.valueOf(result, 0, resultIndex + 1);
                            } else {
                                return "";
                            }
                        }
                        ch = result[i];
                        result[i++] = stack[top--];
                        result[++resultIndex] = ch;
                    } else {
                        return "";
                    }
                } else {
                    return "";
                }
            } else {
                result[++resultIndex] = (stack[top--]);
            }
        }
        if (top == -1) {
            return String.valueOf(result, 0, resultIndex + 1);
        }

        return "";
    }

    public String reorganizeString(String s) {
        int len = s.length();
        int[] counts = new int[26];
        for (int i = 0; i < len; i++) {
            counts[s.charAt(i) - 'a'] += 100;
        }
        for (int i = 0; i < 26; i++) {
            counts[i] += i;
        }
        Arrays.sort(counts);

        char[] chars = new char[len];
        int index = 1;
        for (int charCount : counts) {
            int cnt = charCount / 100;
            char ch = (char)('a' + charCount % 100);
            if (cnt > (len + 1) / 2) {
                return "";
            }
            for (int i = 0; i < cnt; i++) {
                if (index >= len) {
                    index = 0;
                }
                chars[index] = ch;
                index += 2;
            }

        }

        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        ReorganizeString reorganizeString = new ReorganizeString();
        Assert.assertEquals("bcacadadababab", reorganizeString.reorganizeString("aaacbdbaaacbdb"));
        Assert.assertEquals("babc", reorganizeString.reorganizeString("abbc"));
        Assert.assertEquals("babab", reorganizeString.reorganizeString("baabb"));
        Assert.assertEquals("", reorganizeString.reorganizeString("baabbb"));
    }
}
