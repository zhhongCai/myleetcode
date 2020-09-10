package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode 1451
 * @Author: theonecai
 * @Date: Create in 2020/9/10 20:00
 * @Description:
 */
public class ArrangeWords {

    public String arrangeWords(String text) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(text.toLowerCase().split(" ")).sorted(Comparator.comparingInt(String::length))
                .forEach(str -> sb.append(str).append(' '));
        sb.deleteCharAt(sb.length() - 1);
        sb.replace(0, 1, String.valueOf((char)(sb.charAt(0) - 'a' + 'A')));

        return sb.toString();
    }


    public static void main(String[] args) {
        ArrangeWords arrangeWords = new ArrangeWords();
        Assert.assertEquals("L ok test split arrays stream tolowcase",
                arrangeWords.arrangeWords("Arrays stream test tolowcase split ok l"));
    }
}
