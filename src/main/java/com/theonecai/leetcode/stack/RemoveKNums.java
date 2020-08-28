package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 402
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/30 21:44
 * @Description:
 */
public class RemoveKNums {

    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0) {
            return num;
        }
        if (num.length() <= k) {
            return "";
        }
        // 递增栈
        Stack<Character> incStack = new Stack<>();
        incStack.push(num.charAt(0));
        int removeCount = 0;
        char ch;
        for (int i = 1; i < num.length(); i++) {
            ch = num.charAt(i);
            if (!incStack.isEmpty() && ch < incStack.peek() && removeCount < k) {
                while (!incStack.isEmpty()) {
                    if (removeCount >= k) {
                        break;
                    }
                    if (ch >= incStack.peek()) {
                        break;
                    }
                    incStack.pop();
                    removeCount++;
                }
            }
            incStack.push(ch);
        }
        while(removeCount < k && !incStack.isEmpty()) {
            incStack.pop();
            removeCount++;
        }

        return stackValueToString(incStack);
    }

    private String stackValueToString(Stack<Character> incStack) {
        StringBuilder sb = new StringBuilder();
        boolean firstNotZero = false;
        char ch;
        for (int i = 0; i < incStack.size(); i++) {
            ch = incStack.elementAt(i);
            if (ch != '0' && !firstNotZero) {
                firstNotZero = true;
            }
            if (firstNotZero) {
                sb.append(ch);
            }
        }
        if (!firstNotZero) {
            sb.append('0');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveKNums removeKNums = new RemoveKNums();
        Assert.assertEquals("", removeKNums.removeKdigits("", 2));
        Assert.assertEquals("", removeKNums.removeKdigits("1", 2));
        Assert.assertEquals("241", removeKNums.removeKdigits("102481", 2));
        Assert.assertEquals("12241", removeKNums.removeKdigits("122481", 1));
        Assert.assertEquals("23820982", removeKNums.removeKdigits("239820982", 1));
        Assert.assertEquals("2320982", removeKNums.removeKdigits("239820982", 2));
        Assert.assertEquals("220982", removeKNums.removeKdigits("239820982", 3));
        Assert.assertEquals("1219", removeKNums.removeKdigits("1432219", 3));
        Assert.assertEquals("200", removeKNums.removeKdigits("10200", 1));
        Assert.assertEquals("0", removeKNums.removeKdigits("10", 1));
        Assert.assertEquals("0", removeKNums.removeKdigits("100000000000", 1));
        Assert.assertEquals("22", removeKNums.removeKdigits("2222", 2));
        Assert.assertEquals("22271022", removeKNums.removeKdigits("10222781022", 2));
        Assert.assertEquals("2", removeKNums.removeKdigits("10222781022", 8));
    }
}
