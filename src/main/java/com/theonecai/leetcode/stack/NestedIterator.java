package com.theonecai.leetcode.stack;

/**
 * leetcode 341
 * @Author: theonecai
 * @Date: Create in 2020/7/31 21:49
 * @Description:
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    private Stack<Integer> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        push(nestedList);
    }

    private void push(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return;
        }

        for (int i = nestedList.size() - 1; i >= 0; i--) {
            NestedInteger nestedInteger = nestedList.get(i);
            if (nestedInteger.isInteger()) {
                stack.push(nestedInteger.getInteger());
            } else {
                push(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return stack.pop();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }


    static class NestedInteger {
        private Integer val;
        private List<NestedInteger> list;

        public NestedInteger(Integer val, List<NestedInteger> list) {
            this.val = val;
            this.list = list;
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return val != null;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return isInteger() ? val : null;
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return isInteger() ? null : list;
        }
    }

    public static void main(String[] args) {
        List<NestedInteger> list = new ArrayList<>();
        list.add(new NestedInteger(1, null));

        List<NestedInteger> list2 = new ArrayList<>();
        list2.add(new NestedInteger(2, null));
        list2.add(new NestedInteger(4, null));
        List<NestedInteger> list3 = new ArrayList<>();
        list3.add(new NestedInteger(5, null));
        list2.add(new NestedInteger(null, list3));

        list.add(new NestedInteger(null, list2));
        list.add(new NestedInteger(3, null));
        NestedIterator it = new NestedIterator(list);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}


/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
