package com.theonecai.leetcode.backtrace;

import org.junit.Assert;

/**
 * leetcode 306
 */
public class IsAdditiveNumber {

    private int n;
    private int maxLen;
    public boolean isAdditiveNumber(String num) {
        n = num.length();
        if (num.length() < 3) {
            return false;
        }
        maxLen = (n - 1) / 2;
        return dfs(num.toCharArray(), 1);
    }

    private boolean dfs(char[] num, int firstLen) {
        if (firstLen > maxLen) {
            return false;
        }
        if (firstLen > 1 && num[0] == '0') {
            return false;
        }

        int[] first = new int[]{0, firstLen};
        int[] second = new int[]{firstLen, 0};
        for (int i = 1; i <= maxLen; i++) {
            if (i > 1 && num[firstLen] == '0') {
                break;
            }
            second[1] = firstLen + i;
            if (check(num, first, second)) {
                return true;
            }
        }

        return dfs(num, firstLen + 1);
    }

    private boolean check(char[] num, int[] first, int[] second) {
        String third = null;
        int i = second[1];
        while (i < num.length) {
            third = add(num, first, second);
            if (i + third.length() > num.length) {
                return false;
            }
            for (int j = 0, k = i; j < third.length(); j++, k++) {
                if (third.charAt(j) != num[k]) {
                    return false;
                }
            }
            first = second;
            second = new int[]{second[1], second[1] + third.length()};
            i += third.length();
        }
        return true;
    }

    private String add(char[] num, int[] first, int[] second) {
        StringBuilder sum = new StringBuilder();
        int i = first[1] - 1;
        int j = second[1] - 1;
        int carry = 0;
        int v = 0;
        for (; i >= first[0] || j >= second[0] ; i--, j--) {
            v = (i < first[0] ? 0 : (num[i] - '0')) +
                    (j < second[0] ? 0 : (num[j] - '0')) + carry;
            carry = v / 10;
            v %= 10;
            sum.append((char)(v + '0'));
        }
        if (carry > 0) {
            sum.append((char)(carry + '0'));
        }
        return sum.reverse().toString();
    }

    public static void main(String[] args) {
        IsAdditiveNumber isAdditiveNumber = new IsAdditiveNumber();

        Assert.assertTrue(isAdditiveNumber.isAdditiveNumber("112358"));
        Assert.assertTrue(isAdditiveNumber.isAdditiveNumber("0000"));
        Assert.assertFalse(isAdditiveNumber.isAdditiveNumber("1023"));
        Assert.assertFalse(isAdditiveNumber.isAdditiveNumber("111"));
        Assert.assertTrue(isAdditiveNumber.isAdditiveNumber("199100199"));
    }
}
