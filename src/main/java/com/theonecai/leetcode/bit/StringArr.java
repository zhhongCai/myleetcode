package com.theonecai.leetcode.bit;

import com.theonecai.leetcode.util.RunUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 784
 */
public class StringArr {

    public List<String> stringList(String s) {
        List<String> result = new LinkedList<>();

        backtrace(s, new char[s.length()], 0, result);

        return result;
    }

    private void backtrace(String s, char[] chars, int index, List<String> result) {
        if (index == s.length()) {
            result.add(String.valueOf(chars));
            return;
        }
        char ch = s.charAt(index);
        chars[index] = s.charAt(index);
        backtrace(s, chars, index + 1, result);
        if (ch >= 'a' && ch <= 'z') {
            chars[index] = (char)(s.charAt(index) - 'a' + 'A');
            backtrace(s, chars, index + 1, result);
        }
        if (ch >= 'A' && ch <= 'Z') {
            chars[index] = (char)(s.charAt(index) - 'A' + 'a');
            backtrace(s, chars, index + 1, result);
        }
    }

    public static void main(String[] args) {
        StringArr stringArr = new StringArr();
        RunUtil.runAndPrintCostTime(() -> {
            List<String> list = stringArr.stringList("abcdefghijklmn");
        });
//        list.forEach(System.out::println);
    }
}
