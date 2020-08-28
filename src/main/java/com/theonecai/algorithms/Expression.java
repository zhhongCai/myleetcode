package com.theonecai.algorithms;

import com.google.common.collect.Lists;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序表达式->前序表达式(波兰序)
 * 中序表达式->后序表达式(逆波兰序)
 *
 * 中序: 11 + (1 + 2) * 3 + 6 / 3 + 8 * 1 - (6 + 4 / 2 + 1) + 5
 * 前序: + - + + + 11 * + 1 2 3 / 6 3 * 8 1 + + 6 / 4 2 1 5
 * 后序: 11 1 2 + 3 * + 6 3 / + 8 1 * + 6 4 2 / + 1 + - 5 +
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/30 19:48
 * @Description:
 */
public class Expression {

    private String midOrderExpr;

    public Expression(String midOrderExpr) {
        this.midOrderExpr = midOrderExpr;
    }

    /**
     * 中序转前序
     * @return
     */
    public List<String> toPreOrder() {
        Stack<Character> stack = new Stack<>();
        List<String> preOrderReversed = new ArrayList<>();
        char currentChar;
        for (int i = midOrderExpr.length() - 1; i >= 0; i--) {
            currentChar = midOrderExpr.charAt(i);
            if (currentChar == ' ') {
                continue;
            }
            if (currentChar == '+' || currentChar == '-') {
                while(!stack.isEmpty()) {
                    char top = stack.peek();
                    if (top == '*' || top == '/') {
                        char c = stack.pop();
                        if (c != '*' && c != '/') {
                            break;
                        }
                        preOrderReversed.add(String.valueOf(c));
                    } else {
                        break;
                    }
                }
                stack.push(currentChar);
            } else if (currentChar == '*' || currentChar == '/' || currentChar == ')') {
                stack.push(currentChar);
            } else if (currentChar == '(') {
                while(!stack.isEmpty()) {
                    char c = stack.pop();
                    if (c == ')') {
                        break;
                    }
                    preOrderReversed.add(String.valueOf(c));
                }
            } else if (currentChar >= '0' && currentChar <= '9') {
                String num = null;
                int end = i;
                for (int j = i - 1; j >= 0; j--) {
                    i--;
                    if (midOrderExpr.charAt(j) < '0' || midOrderExpr.charAt(j) > '9') {
                        i++;
                        num = midOrderExpr.substring(j + 1, end + 1);
                        break;
                    }
                }
                if (num == null) {
                    num = midOrderExpr.substring(0, end + 1);
                }
                preOrderReversed.add(num);
            }
        }
        while (!stack.isEmpty()) {
            preOrderReversed.add(String.valueOf(stack.pop()));
        }

        return Lists.reverse(preOrderReversed);
    }

    /**
     * 计算前序表达式
     * @param preOrderExpr
     * @return
     */
    public int calculatePreOrderExpression(List<String> preOrderExpr) {
        int firstOpNum;
        int secondOpNum;
        Stack<Integer> nums = new Stack<>();
        for (int i = preOrderExpr.size() - 1; i >= 0; i--) {
            String ch = preOrderExpr.get(i);
            if (ch.equals("+")) {
                firstOpNum = nums.pop();
                secondOpNum = nums.pop();
                nums.push(firstOpNum + secondOpNum);
            } else if (ch.equals("-")) {
                firstOpNum = nums.pop();
                secondOpNum = nums.pop();
                nums.push(firstOpNum - secondOpNum);
            } else if (ch.equals("*")) {
                firstOpNum = nums.pop();
                secondOpNum = nums.pop();
                nums.push(firstOpNum * secondOpNum);
            } else if (ch.equals("/")) {
                firstOpNum = nums.pop();
                secondOpNum = nums.pop();
                nums.push(firstOpNum / secondOpNum);
            }  else {
                nums.push(Integer.parseInt(ch));
            }
        }

        return nums.pop();
    }


    /**
     * 中序转后续
     * @return
     */
    public List<String> toPostOrder() {
        Stack<Character> stack = new Stack<>();
        List<String> postOrderReversed = new ArrayList<>();
        char currentChar;
        for (int i = 0; i < midOrderExpr.length(); i++) {
            currentChar = midOrderExpr.charAt(i);
            if (currentChar == ' ') {
                continue;
            }
            if (currentChar == '+' || currentChar == '-') {
                while(!stack.isEmpty()) {
                    char top = stack.peek();
                    if (top == '*' || top == '/' || top == '+' || top == '-') {
                        postOrderReversed.add(String.valueOf(stack.pop()));
                    } else {
                        break;
                    }
                }
                stack.push(currentChar);
            } else if (currentChar == '*' || currentChar == '/' || currentChar == '(') {
                stack.push(currentChar);
            } else if (currentChar == ')') {
                while(!stack.isEmpty()) {
                    char c = stack.pop();
                    if (c == '(') {
                        break;
                    }
                    postOrderReversed.add(String.valueOf(c));
                }
            } else if (currentChar >= '0' && currentChar <= '9') {
                String num = null;
                int start = i;
                for (int j = i + 1; j < midOrderExpr.length(); j++) {
                    i++;
                    if (midOrderExpr.charAt(j) < '0' || midOrderExpr.charAt(j) > '9') {
                        i--;
                        num = midOrderExpr.substring(start, j);
                        break;
                    }
                }
                if (num == null) {
                    num = midOrderExpr.substring(start);
                }
                postOrderReversed.add(num);
            }
        }
        while (!stack.isEmpty()) {
            postOrderReversed.add(String.valueOf(stack.pop()));
        }

        return postOrderReversed;
    }

    /**
     * 计算后续表达式
     * @param postOrderExpr
     * @return
     */
    public int calculatePostOrderExpression(List<String> postOrderExpr) {
        int firstOpNum;
        int secondOpNum;
        Stack<Integer> nums = new Stack<>();
        for (int i = 0; i < postOrderExpr.size(); i++) {
            String ch = postOrderExpr.get(i);
            if (ch.equals("+")) {
                secondOpNum = nums.pop();
                firstOpNum = nums.pop();
                nums.push(firstOpNum + secondOpNum);
            } else if (ch.equals("-")) {
                secondOpNum = nums.pop();
                firstOpNum = nums.pop();
                nums.push(firstOpNum - secondOpNum);
            } else if (ch.equals("*")) {
                secondOpNum= nums.pop();
                firstOpNum = nums.pop();
                nums.push(firstOpNum * secondOpNum);
            } else if (ch.equals("/")) {
                secondOpNum = nums.pop();
                firstOpNum = nums.pop();
                nums.push(firstOpNum / secondOpNum);
            }  else {
                nums.push(Integer.parseInt(ch));
            }
        }

        return nums.pop();
    }


    public static void main(String[] args) {
//        Expression expression = new Expression("3 - 4 + 5");
        Expression expression = new Expression("11 + (1 + 2) * 3 + 6 / 3 + 8 * 1 - (6 + 4 / 2 + 1) + 5");
        List<String> preOrderExpr = expression.toPreOrder();
        System.out.println(expression.midOrderExpr);
        System.out.println(preOrderExpr);
        Assert.assertEquals(26, expression.calculatePreOrderExpression(preOrderExpr));
        List<String> postOrderExpr = expression.toPostOrder();
        System.out.println(postOrderExpr);
        Assert.assertEquals(26, expression.calculatePostOrderExpression(postOrderExpr));
    }
}
