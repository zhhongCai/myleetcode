package com.theonecai.leetcode.backtrace;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * leetcode 2014
 */
public class LongestSubsequenceRepeatedK {

    private String subStr;
    private int n;
    private char[] chars;
    private boolean found;

    /**
     * 子序列长度 <= 7
     * @param s
     * @param k
     * @return
     */
    public String longestSubsequenceRepeatedK(String s, int k) {
        int[] count = new int[26];
        chars = s.toCharArray();
        for (char ch : chars) {
            count[ch - 'a']++;
        }
        //重复出现>=k次的字符
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            while (count[i] >= k) {
                charList.add((char)('a' + i));
                count[i] -= k;
            }
        }

        n = chars.length;
        subStr = "";
        found = false;
        if (charList.isEmpty()) {
            return subStr;
        }
        charList.sort(Comparator.reverseOrder());

        dfs(charList, new char[7], 0, k, new boolean[charList.size()]);

        return subStr;
    }

    private void dfs(List<Character> charList, char[] str, int index, int k, boolean[] seen) {
        if (index >= 7 || found) {
            return;
        }

        for (int i = 0; i < charList.size(); i++) {
            if (seen[i]) {
                continue;
            }

            str[index++] = charList.get(i);
            seen[i] = true;
            if (compareResult(subStr, str, index) < 0 && check(str, index, k)) {
                subStr = new String(str, 0, index);;
            }
            dfs(charList, str, index, k, seen);
            index--;
            seen[i] = false;
        }
    }

    public int compareResult(String res, char[] str, int len) {
        if (res.length() < len) {
            return -1;
        }
        if (res.length() == len) {
            for (int i = 0; i < len; i++) {
                if (res.charAt(i) == str[i]) {
                    continue;
                }
                return res.charAt(i) - str[i];
            }
        }
        return 1;
    }

    /**
     * 检查当前子序列是否满足要求
     * @param str
     * @param len
     * @param k
     * @return
     */
    private boolean check(char[] str, int len, int k) {
        int count = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (str[j] == chars[i]) {
                j++;
            }
            if (j == len) {
                count++;
                if (count >= k) {
                    return true;
                }
                j = 0;
            }
        }
        return count >= k;
    }

    public static void main(String[] args) {
        LongestSubsequenceRepeatedK repeatedK = new LongestSubsequenceRepeatedK();
        Assert.assertEquals("wonder", repeatedK.longestSubsequenceRepeatedK("bwonderwonderwonderwonderwonderwonderwonderwonderb", 8));
        Assert.assertEquals("let", repeatedK.longestSubsequenceRepeatedK("letsleetcode", 2));
        Assert.assertEquals("b", repeatedK.longestSubsequenceRepeatedK("bb", 2));
        Assert.assertEquals("", repeatedK.longestSubsequenceRepeatedK("ab", 2));
        Assert.assertEquals("bbbb", repeatedK.longestSubsequenceRepeatedK("bbabbabbbbabaababab", 3));
        Assert.assertEquals("abaaa", repeatedK.longestSubsequenceRepeatedK("aaabaabaaaabaaaabaaaabaaa", 4));
    }
}
