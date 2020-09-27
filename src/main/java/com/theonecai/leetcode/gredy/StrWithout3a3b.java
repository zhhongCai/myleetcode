package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 984
 * @Author: theonecai
 * @Date: Create in 2020/9/22 09:36
 * @Description:
 */
public class StrWithout3a3b {

    public String strWithout3a3b(int A, int B) {
        StringBuilder sb = new StringBuilder();
        int res = Math.abs(A - B);
        if (A > B) {
            append('a', 'b', res, B, sb);
        } else if (A < B) {
            append('b', 'a', res, A, sb);
        } else {
            for (int i = 0; i < A; i++) {
                sb.append("a").append("b");
            }
        }
        return sb.subSequence(0, A + B).toString();
    }

    private void append(char ch, char ch2, int res, int lessChCount, StringBuilder sb) {
        for (int i = 0; i < res; i++) {
            sb.append(ch).append(ch).append(ch2);
        }
        for (int i = 0; i < lessChCount - res; i++) {
            sb.append(ch).append(ch2);
        }
    }

    public static void main(String[] args) {
        StrWithout3a3b strWithout3a3b = new StrWithout3a3b();
        Assert.assertEquals("aabababab", strWithout3a3b.strWithout3a3b(5, 4));
        Assert.assertEquals("bba", strWithout3a3b.strWithout3a3b(1, 2));
        Assert.assertEquals("bba", strWithout3a3b.strWithout3a3b(1, 2));
        Assert.assertEquals("aabaa", strWithout3a3b.strWithout3a3b(4, 1));
        Assert.assertEquals("aabaa", strWithout3a3b.strWithout3a3b(4, 1));
    }
}
