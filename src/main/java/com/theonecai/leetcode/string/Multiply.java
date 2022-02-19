package com.theonecai.leetcode.string;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 43
 */
public class Multiply {

    public String multiply(String a, String b) {
        int an = a.length();
        char[] c = new char[an + b.length() + 1];
        Arrays.fill(c, '0');
        char[] ar = a.toCharArray();
        char[] br = b.toCharArray();
        for (int i = an - 1, k = 0; i >= 0; i--, k++) {
            multiply(ar[i], k, br, c);
        }
        int i = 0;
        while (i <c.length && c[i] == '0') {
            i++;
        }
        if (i >= c.length) {
            return "0";
        }
        return String.valueOf(c, i, c.length - i);
    }

    private void multiply(char ch, int len, char[] br, char[] c) {
        int carry = 0;
        int j = c.length - 1 - len;
        for (int i = br.length - 1; i >= 0; i--, j--) {
            int m = (ch - '0') * (br[i] - '0') + (c[j] - '0') + carry;
            carry = m / 10;
            m %= 10;
            c[j] = (char)(m + '0');
        }
        while (carry > 0) {
            int m = (c[j] - '0') + carry;
            c[j--] = (char)(m % 10 + '0');
            carry = m / 10;
        }
    }

    public static void main(String[] args) {
        Multiply multiply = new Multiply();
        Assert.assertEquals("6", multiply.multiply("2", "3"));
        Assert.assertEquals("56088", multiply.multiply("123", "456"));
    }
}
