package com.theonecai.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * leetcode 895 官方题解
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/31 22:23
 * @Description:
 */
public class FreqStack {
    private int maxCount;
    private Map<Integer, Integer> countMap;
    private Map<Integer, Stack<Integer>> countEqMap;

    public FreqStack() {
        maxCount = 0;
        countMap = new HashMap<>();
        countEqMap = new HashMap<>();
    }

    public void push(int x) {
        int count = countMap.getOrDefault(x, 0) + 1;
        if (count > maxCount) {
            maxCount = count;
        }
        countMap.put(x, count);
        countEqMap.computeIfAbsent(count,v ->new Stack<>()).push(x);
    }

    public int pop() {
        int val = countEqMap.get(maxCount).pop();
        countMap.put(val, countMap.get(val) - 1);
        if (countEqMap.get(maxCount).size() == 0) {
            maxCount--;
        }
        return val;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();

        String[] cmd = {"push","push","push","push","push","push","pop","pop","pop","pop"};
        int[] vals = {5, 7, 5, 7, 4, 5};

        String c;
        int index = 0;
        for (int i = 0; i < cmd.length; i++) {
            c = cmd[i];
            if ("push".equals(c)) {
                freqStack.push(vals[index++]);
            } else if ("pop".equals(c)) {
                System.out.println(freqStack.pop());
            }
        }
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
