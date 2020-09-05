package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * leetcode 524
 *
 * @Author: theonecai
 * @Date: Create in 2020/9/5 16:33
 * @Description:
 */
public class FindLongestWord {

    public String findLongestWord(String s, List<String> d) {
        String str = "";
        if (d == null || d.size() < 1) {
            return str;
        }

        for (String word : d) {
            if (word.length() > s.length()) {
                continue;
            }
            if (match(word, s)) {
                if (word.length() > str.length()) {
                    str = word;
                } else if (word.length() == str.length()) {
                    str = word.compareTo(str) < 0 ? word : str;
                }
            }
        }

        return str;

    }

    public String findLongestWord2(String s, List<String> d) {
        String str = "";
        if (d == null || d.size() < 1) {
            return str;
        }
        d.sort((o1, o2) -> {
            int res = o2.length() - o1.length();
            if (res == 0) {
                return o1.compareTo(o2);
            }
            return res;
        });

        if (d.get(d.size() - 1).length() > s.length()) {
            return str;
        }

        for (String word : d) {
            if (word.length() > s.length()) {
                continue;
            }
            if (match(word, s)) {
                str = word;
                break;
            }
        }

        return str;

    }


    private boolean match(String word, String s) {
        int i = 0;
        int j = 0;
        while (i < word.length() && j < s.length()) {
            if (word.charAt(i) == s.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == word.length();
    }

    public static void main(String[] args) {
        FindLongestWord findLongestWord = new FindLongestWord();
        Assert.assertEquals("apple",
                findLongestWord.findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea")));
        Assert.assertEquals("a",
                findLongestWord.findLongestWord("abpcplea", Arrays.asList("b", "a", "c", "d")));
    }

}
