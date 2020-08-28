package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode 224
 *
 * @Author: thonecai
 * @Date: Create in 2020/7/29 21:00
 * @Description:
 */
public class SimpleCalculator {

    public int calculate(String s) {
        Stack<Character> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == ')') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == '(') {
                while(!stack.isEmpty()) {
                    char c = stack.pop();
                    if (c == ')') {
                        break;
                    }
                    list.add(String.valueOf(c));
                }
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                String num = null;
                int end = i;
                for (int j = i - 1; j >= 0; j--) {
                    i--;
                    if (s.charAt(j) < '0' || s.charAt(j) > '9') {
                        i++;
                        num = s.substring(j + 1, end + 1);
                        break;
                    }
                }
                if (num == null) {
                    num = s.substring(0, end + 1);
                }
                list.add(num);
            }
        }
        while (!stack.isEmpty()) {
           list.add(String.valueOf(stack.pop()));
        }

//        System.out.println(list);

        int a;
        int b;
        Stack<Integer> nums = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            String ch = list.get(i);
            if (ch.equals("+")) {
                b = nums.pop();
                a = nums.pop();
                nums.push(a + b);
            } else if (ch.equals("-")) {
                a = nums.pop();
                b = nums.pop();
                nums.push(a - b);
            }  else {
                nums.push(Integer.parseInt(ch));
            }
        }

        return nums.pop();
    }

    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();
        /**
         * 4 3 2 1 + -  1 ++
         * + + 1 - + 1 2 3 4
         *  + 9 14
         */
        Assert.assertEquals(5, calculator.calculate(" 1 + (1 + 2 - 3) + 4"));
        Assert.assertEquals(2, calculator.calculate("1 + 1"));
        Assert.assertEquals(23, calculator.calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}
