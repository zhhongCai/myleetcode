package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 402
 * @See com.theonecai.leetcode.stack.RemoveKNums
 * @Author: theonecai
 * @Date: Create in 2020/9/17 19:27
 * @Description:
 */
public class RemoveKdigits {
    public String removeKdigits(String num, int k) {
        if (k == 0) {
            return num;
        }
        if (num.length() == k) {
            return "0";
        }

        int remain = k;
        int resultLen = num.length() - k;
        char[] result = new char[resultLen];
        int index = 0;
        for (int i = 0; i < num.length(); i++) {
            while (index > 0 && remain > 0 && num.charAt(i) < result[index - 1]) {
                index--;
                remain--;
            }
            if (index == resultLen && remain > 0 && num.charAt(i) >= result[index - 1]) {
                remain--;
                continue;
            }
            result[index++] = num.charAt(i);
        }
        int i = 0;
        while (i < resultLen && result[i] == '0') {
            i++;
        }
        if (i == resultLen) {
            return "0";
        }

        return String.valueOf(result, i, resultLen - i);
    }

    public static void main(String[] args) {
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals("1219", removeKdigits.removeKdigits("1432219", 3));
        Assert.assertEquals("200", removeKdigits.removeKdigits("10200", 1));
        Assert.assertEquals("0", removeKdigits.removeKdigits("100", 1));
        Assert.assertEquals("0", removeKdigits.removeKdigits("10", 2));
        Assert.assertEquals("11", removeKdigits.removeKdigits("112", 1));
    }
}
