package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 1404
 */
public class NumSteps {

    private int count;
    public int numSteps(String s) {
        count = 0;
        while (s.length() != 1 || s.charAt(0) != '1') {
            if (s.charAt(s.length() - 1) == '0') {
                s = divByTow(s);
            } else {
                s = addOne(s);
            }
        }
        return count;
    }

    private String divByTow(String s) {
        int len = s.length();
        int last = len - 1;
        for (; last >= 0; last--) {
            if (s.charAt(last) == '1') {
                break;
            }
            count++;
        }
        if (last == -1) {
            return "0";
        }
        return s.substring(0, last + 1);
    }

    private String addOne(String s) {
        count++;
        int overflow = 1;
        StringBuilder sb = new StringBuilder("0");
        for (int i = s.length() - 2; i >= 0; i--) {
            if (overflow == 1) {
                if (s.charAt(i) == '1') {
                    sb.append('0');
                } else {
                    sb.append('1');
                    overflow = 0;
                }
            } else {
                sb.append(s.charAt(i));
            }
        }
        if (overflow == 1) {
            sb.append(overflow);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        NumSteps numSteps = new NumSteps();
        Assert.assertEquals(6, numSteps.numSteps("1101"));
        Assert.assertEquals(6, numSteps.numSteps("11000"));
        Assert.assertEquals(1, numSteps.numSteps("10"));
        Assert.assertEquals(0, numSteps.numSteps("1"));
    }
}
