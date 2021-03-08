package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 131
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/24 21:31
 * @Description:
 */
public class PalindromeStr {

    private boolean[][] dp;

    public List<List<String>> partition(String s) {
        List<List<String>> lists = new ArrayList<>();

        // dp[i][j] 表示s[i~j]是否为回文串
        dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], true);
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
            }
        }

        palindromeSubStr(s, 0, new ArrayList<>(s.length()), lists);

        return lists;
    }

    private void palindromeSubStr(String str, int index, List<String> list, List<List<String>> lists) {
        if (index == str.length()) {
            lists.add(new ArrayList<>(list));
            return;
        }

        int n = str.length() - index;
        for (int i = 1; i <= n; i++) {
            String subStr = str.substring(index, index + i);
            if (dp[index][index + i - 1]) {
                list.add(subStr);
                palindromeSubStr(str, index + i, list, lists);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindromeSubStr(String subStr) {
        if (subStr.length() == 1) {
            return true;
        }
        int left = 0;
        int right = subStr.length() - 1;
        while (left < right) {
            if (subStr.charAt(left++) != subStr.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> partition2(String s) {
        List<List<String>> result = new ArrayList<>();

        for (int i = 1; i <= s.length(); i++) {
            String str = s.substring(0, i);
            if (isPalindromeSubStr(str)) {
                if (i < s.length()) {
                    List<List<String>> tmp = partition(s.substring(i));
                    if (tmp != null && !tmp.isEmpty()) {
                        for (List<String> strings : tmp) {
                            strings.add(0, str);
                            result.add(strings);
                        }
                    }
                } else {
                    List<String> list = new LinkedList<>();
                    list.add(str);
                    result.add(list);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PalindromeStr palindromeStr = new PalindromeStr();
        List<List<String>> list = palindromeStr.partition("aaaab");
        list.forEach(System.out::println);
    }
}
