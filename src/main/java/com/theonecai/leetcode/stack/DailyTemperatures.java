package com.theonecai.leetcode.stack;

import com.theonecai.algorithms.ArrayUtil;

import java.util.Stack;

/**
 * leetcode 739
 * @Author: theonecai
 * @Date: Create in 2020/6/20 22:01
 * @Description:
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        if (T == null) {
            return new int[0];
        }

        int len = T.length;
        int[] result = new int[len];
        int i, j;
        for (i = 0; i < len; i++) {
            for (j = i + 1; j < len; j++) {
                if (T[i] < T[j]) {
                    break;
                }
            }
            if (j == len) {
                result[i] = 0;
            } else {
                result[i] = j - i;
            }
        }
        return result;
    }

    public int[] dailyTemperatures2(int[] T) {
        if (T == null) {
            return new int[0];
        }

        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(T.length - 1);
        result[T.length - 1] = 0;
        for (int i = T.length - 2; i >= 0; i--) {
            if (T[i] < T[stack.peek()]) {
                result[i] = 1;
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = stack.peek() - i;
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] t = {73, 74, 75, 71, 69, 72, 76, 73};

        DailyTemperatures dt = new DailyTemperatures();
        int[] result = dt.dailyTemperatures(t);
        ArrayUtil.print(result);

        result = dt.dailyTemperatures2(t);
        ArrayUtil.print(result);

        int[] t2 = {89,62,70,58,47,47,46,76,100,70};

        result = dt.dailyTemperatures(t2);
        ArrayUtil.print(result);

        result = dt.dailyTemperatures2(t2);
        ArrayUtil.print(result);

    }
}
