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
        return dfs(num, 1);
    }

    private boolean dfs(String num, int firstLen) {
        if (firstLen > maxLen) {
            return false;
        }
        if (firstLen > 1 && num.charAt(0) == '0') {
            return false;
        }

        for (int i = 1; i <= maxLen; i++) {
            if (i > 1 && num.charAt(firstLen) == '0') {
                break;
            }
            if (check(num, firstLen, i)) {
                return true;
            }
        }

        return dfs(num, firstLen + 1);
    }

    private boolean check(String num, int firstLen, int secondLen) {
        long first = Long.parseLong(num.substring(0, firstLen));
        long second = Long.parseLong(num.substring(firstLen, firstLen + secondLen));
        long third = first + second;
        int i = firstLen + secondLen;
        while (i < num.length()) {
            String th = String.valueOf(third);
            if (i + th.length() > num.length() || num.length() - i < th.length()) {
                return false;
            }
            if (!th.equals(num.substring(i, i + th.length()))) {
                return false;
            }
            first = second;
            second = third;
            third = first + second;
            i += th.length();
        }
        return true;
    }

    public static void main(String[] args) {
        IsAdditiveNumber isAdditiveNumber = new IsAdditiveNumber();
        Assert.assertTrue(isAdditiveNumber.isAdditiveNumber("0000"));
        Assert.assertFalse(isAdditiveNumber.isAdditiveNumber("1023"));
        Assert.assertFalse(isAdditiveNumber.isAdditiveNumber("111"));
        Assert.assertTrue(isAdditiveNumber.isAdditiveNumber("112358"));
        Assert.assertTrue(isAdditiveNumber.isAdditiveNumber("199100199"));
    }
}
