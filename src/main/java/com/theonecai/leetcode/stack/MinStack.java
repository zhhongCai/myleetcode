package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 155
 * @Author: zhenghong.cai
 * @Date: Create in 6/5/21 21:48
 * @Description:
 */
public class MinStack {

    List<Node> stack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayList<>();
    }

    public void push(int val) {
        if (stack.size() == 0) {
            stack.add(new Node(val, val));
        } else {
            int min = Math.min(stack.get(stack.size() - 1).min, val);
            stack.add(new Node(val, min));
        }
    }

    public void pop() {
        if (stack.size() > 0) {
            stack.remove(stack.size() - 1);
        }
    }

    public int top() {
        return stack.get(stack.size() - 1).val;
    }

    public int getMin() {
        return stack.get(stack.size() - 1).min;
    }

    private static class Node {
        private int val;
        private int min;

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-3);
        minStack.push(0);
        minStack.push(2);
        Assert.assertEquals(-3, minStack.getMin());
        minStack.pop();
        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(-2, minStack.getMin());
    }
}
