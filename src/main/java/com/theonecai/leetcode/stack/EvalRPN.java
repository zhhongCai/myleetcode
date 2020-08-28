package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 150
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/26 11:19
 * @Description:
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int result;
        int a;
        int b;
        for (String token : tokens) {
            if ("+".equals(token)) {
                a = stack.pop();
                b = stack.pop();
                result = b + a;
                stack.push(result);
            } else if ("-".equals(token)) {
                a = stack.pop();
                b = stack.pop();
                result = b - a;
                stack.push(result);
            } else if ("*".equals(token)) {
                a = stack.pop();
                b = stack.pop();
                result = b * a;
                stack.push(result);
            } else if ("/".equals(token)) {
                a = stack.pop();
                b = stack.pop();
                result = b / a;
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        EvalRPN evalRPN = new EvalRPN();
        String[] tokens = {"2", "1", "+", "3", "*"};
        Assert.assertEquals(9, evalRPN.evalRPN(tokens));

        String[] tokens2 = {"4", "13", "5", "/", "+"};
        Assert.assertEquals(6, evalRPN.evalRPN(tokens2));

        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        Assert.assertEquals(22, evalRPN.evalRPN(tokens3));
    }
}
