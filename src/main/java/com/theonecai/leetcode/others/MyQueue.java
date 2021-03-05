package com.theonecai.leetcode.others;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 232
 * @Author: theonecai
 * @Date: Create in 2021/3/5 20:35
 * @Description:
 */
public class MyQueue {
    private Stack<Integer> inStack;

    private Stack<Integer> outStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    private void toOut() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outStack.isEmpty()) {
            toOut();
        }
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (outStack.isEmpty()) {
            toOut();
        }
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.size() == 0 && outStack.size() == 0;
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        Assert.assertEquals(1, myQueue.peek());
        Assert.assertEquals(1, myQueue.pop());
        Assert.assertEquals(2, myQueue.pop());
        Assert.assertEquals(3, myQueue.pop());
        Assert.assertTrue(myQueue.empty());
        myQueue.push(4);
        Assert.assertEquals(4, myQueue.peek());
        myQueue.push(5);
        Assert.assertEquals(4, myQueue.pop());
        Assert.assertFalse(myQueue.empty());
        Assert.assertEquals(5, myQueue.peek());

    }
}
