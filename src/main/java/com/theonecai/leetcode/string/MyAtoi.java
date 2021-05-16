package com.theonecai.leetcode.string;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 8
 * @Author: theonecai
 * @Date: Create in 5/16/21 20:14
 * @Description:
 */
public class MyAtoi {
    private static class Automation {
        int sign = 1;
        long result = 0;
        private String currentState;
        private final static String STATE_START = "start";
        private final static String STATE_SIGN = "sign";
        private final static String STATE_NUMBER = "number";
        private final static String STATE_END = "end";
        private Map<String, String[]> stateTable = new HashMap<>();

        public Automation() {
            //Map<current state,  ' ', '+' or '-', '0'~'9', others: next state>
            stateTable.put(STATE_START, new String[]{STATE_START, STATE_SIGN, STATE_NUMBER, STATE_END});
            //
            stateTable.put(STATE_SIGN, new String[]{STATE_END, STATE_END, STATE_NUMBER, STATE_END});
            //
            stateTable.put(STATE_NUMBER, new String[]{STATE_END, STATE_END, STATE_NUMBER, STATE_END});
            //
            stateTable.put(STATE_END, new String[]{STATE_END, STATE_END, STATE_END, STATE_END, STATE_END});
            // 初始状态
            currentState = STATE_START;
        }

        public void handle(char ch) {
            currentState = stateTable.get(currentState)[getNextStateIndex(ch)];
            if (currentState.equals(STATE_NUMBER)) {
                result = result * 10 + ch - '0';
                result = sign == 1 ? Math.min(result, (long) Integer.MAX_VALUE) : Math.min(result, -(long) Integer.MIN_VALUE);
            } else if (currentState.equals(STATE_SIGN)) {
                sign = ch == '-' ? -1 : 1;
            }
        }

        private int getNextStateIndex(char ch) {
            if (' ' == ch) {
                return 0;
            } else if ('+' == ch || '-' == ch) {
                return 1;
            } else if (Character.isDigit(ch)) {
                return 2;
            }
            return 3;
        }
    }

    public int myAtoi(String s) {
        Automation automation = new Automation();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            automation.handle(ch);
        }
        return (int)(automation.sign * automation.result);
    }

    public static void main(String[] args) {
        MyAtoi myAtoi = new MyAtoi();
        Assert.assertEquals(0, myAtoi.myAtoi("- 21474836480"));
        Assert.assertEquals(0, myAtoi.myAtoi("+-12"));
        Assert.assertEquals(Integer.MAX_VALUE, myAtoi.myAtoi("91283472332"));
        Assert.assertEquals(Integer.MIN_VALUE, myAtoi.myAtoi("-91283472332"));
        Assert.assertEquals(42, myAtoi.myAtoi("42"));
        Assert.assertEquals(-42, myAtoi.myAtoi("-42"));
        Assert.assertEquals(4193, myAtoi.myAtoi("4193 with word"));
        Assert.assertEquals(0, myAtoi.myAtoi(" word 4193 with word"));
        Assert.assertEquals(4193, myAtoi.myAtoi("    4193- with word"));
        Assert.assertEquals(4193, myAtoi.myAtoi("    4193+ with word"));
        Assert.assertEquals(4193, myAtoi.myAtoi("  +4193+ with word"));
        Assert.assertEquals(-4193, myAtoi.myAtoi("  -4193+ with word"));
    }

}
