package com.theonecai.leetcode.backtrace;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 282
 */
public class AddOperators {
    private List<String> res;

    public List<String> addOperators(String num, int target) {
        res = new ArrayList<>();
        if (num.length() == 1) {
            if (num.charAt(0) - '0' == target) {
                res.add(num);
            }
            return res;
        }

        char[] chars = num.toCharArray();

        dfs(chars, 0, target, new StringBuilder());

        return res;
    }

    private void dfs(char[] chars, int start, int target, StringBuilder bf) {
        if (start == chars.length - 1) {
            bf.append(chars[start]);
            String str = bf.toString();
//            System.out.println(str);
            if (checkValue(str, target)) {
                res.add(str);
            }
            return;
        }
        int len2 = bf.length();
        bf.append(chars[start]);
        dfs(chars, start + 1, target, bf);
        bf.delete(len2 + 1, bf.length());

        int len = bf.length();
        bf.append("+");
        dfs(chars, start + 1, target, bf);
        bf.delete(len, bf.length());

        bf.append("-");
        dfs(chars, start + 1, target, bf);
        bf.delete(len, bf.length());

        bf.append("*");
        dfs(chars, start + 1, target, bf);
        bf.delete(len, bf.length());
    }

    private boolean checkValue(String exp, int target) {
        return calculatePreOrderExpression(toPreOrder(exp)) == target;
    }

    public List<String> toPreOrder(String exp) {
        Stack<Character> stack = new Stack<>();
        List<String> preOrderReversed = new ArrayList<>();
        char currentChar;
        for (int i = exp.length() - 1; i >= 0; i--) {
            currentChar = exp.charAt(i);
            if (currentChar == '+' || currentChar == '-') {
                while(!stack.isEmpty()) {
                    char top = stack.peek();
                    if (top == '*') {
                        char c = stack.pop();
                        if (c != '*') {
                            break;
                        }
                        preOrderReversed.add(String.valueOf(c));
                    } else {
                        break;
                    }
                }
                stack.push(currentChar);
            } else if (currentChar == '*') {
                stack.push(currentChar);
            } else if (currentChar >= '0' && currentChar <= '9') {
                String num = null;
                int end = i;
                for (int j = i - 1; j >= 0; j--) {
                    i--;
                    if (exp.charAt(j) < '0' || exp.charAt(j) > '9') {
                        i++;
                        num = exp.substring(j + 1, end + 1);
                        break;
                    }
                }
                if (num == null) {
                    num = exp.substring(0, end + 1);
                }
                preOrderReversed.add(num);
            }
        }
        while (!stack.isEmpty()) {
            preOrderReversed.add(String.valueOf(stack.pop()));
        }
        List<String> expList = new ArrayList<>(preOrderReversed.size());
        for (int i = preOrderReversed.size() - 1; i >= 0; i--) {
            expList.add(preOrderReversed.get(i));
        }

        return expList;
    }

    /**
     * 计算前序表达式
     * @param preOrderExpr
     * @return
     */
    public long calculatePreOrderExpression(List<String> preOrderExpr) {
        long firstOpNum;
        long secondOpNum;
        Stack<Long> nums = new Stack<>();
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
            }  else {
                if (ch.length() > 1 && ch.charAt(0) == '0') {
                    return Long.MAX_VALUE;
                }
                nums.push(Long.parseLong(ch));
            }
        }

        return nums.pop();
    }

    public static void main(String[] args) {
        AddOperators addOperators = new AddOperators();
        //["3*456-2-3*74+90","3+4*5*62+3-7+4-9+0","3+4*5*62+3-7+4-9-0","3+4*5*62-3+7-4-9+0","3+4*5*62-3+7-4-9-0"]
        List<String> list;
        //[,,,,,,,,,,]
        //[,"1*0*0-0+9",,,,,,,,,,"100*0+9"]
        list = addOperators.addOperators("10009", 9);
        System.out.println(list);
        list = addOperators.addOperators("3456237490", 1234);
        System.out.println(list);
        list = addOperators.addOperators("123", 6);
        System.out.println(list);
        list = addOperators.addOperators("105", 5);
        System.out.println(list);
        list = addOperators.addOperators("000", 0);
        System.out.println(list);
    }
}
