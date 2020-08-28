package com.theonecai.leetcode.backtrace;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 17
 * @Author: theonecai
 * @Date: Create in 2020/7/13 18:52
 * @Description:
 */
public class LetterCombinations {
    private String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }

        List<String> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        letterCombinations(digits, 0, sb, list);

        return list;
    }

    public void letterCombinations(String nums, int numsIndex, StringBuilder sb, List<String> list) {
        if (sb.length() == nums.length()) {
            list.add(sb.toString());
            return;
        }

        String chs = keys[nums.charAt(numsIndex) - '0'];
        for (int i = 0; i < chs.length(); i++) {
            sb.append(chs.charAt(i));
            letterCombinations(nums, numsIndex + 1, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinations printDigitData = new LetterCombinations();
        List<String> list = printDigitData.letterCombinations("22222");
        list.forEach(System.out::println);
    }
}
