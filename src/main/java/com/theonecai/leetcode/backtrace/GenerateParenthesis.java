package com.theonecai.leetcode.backtrace;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *  leetcode 22
 * @Author: theonecai
 * @Date: Create in 2020/7/10 20:27
 * @Description:
 */
public class GenerateParenthesis {
    private char[] str;

    List<String> result;

    public List<String> generateParenthesis(int n) {
        if (n < 1) {
            return Collections.emptyList();
        }

        result = new LinkedList<>();
        str = new char[n * 2];

        f(0, n - 1, n, '(');

        return result;
    }

    public void f(int currentIndex, int leftCount, int rightCount, char ch) {
        str[currentIndex] = ch;
        if (currentIndex == str.length - 1) {
            if (isOk(currentIndex)) {
                result.add(String.valueOf(str));
            }
            return;
        }
        if (leftCount > 0) {
            f(currentIndex + 1, leftCount - 1, rightCount, '(');
        }
        if (rightCount > 0) {
            f(currentIndex + 1, leftCount, rightCount - 1, ')');
        }
    }

    private boolean isOk(int currentIndex) {
        if (str[0] == ')' || str[str.length - 1] == '(') {
            return false;
        }
        int top = 0;
        for (int i = 1; i < currentIndex; i++) {
            if (str[i] == '(') {
                top++;
                continue;
            }
            if (top < 0) {
                return false;
            }
            top--;
        }

        return top <= 0;
    }

    public static void main(String[] args) {
        GenerateParenthesis gen = new GenerateParenthesis();
        for (int i = 1; i < 5; i++) {
            List<String> list = gen.generateParenthesis(i);
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println();
        }
    }
}
