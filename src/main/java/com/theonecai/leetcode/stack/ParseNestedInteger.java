package com.theonecai.leetcode.stack;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode 385
 *
 * @Author: thonecai
 * @Date: Create in 2020/8/4 20:48
 * @Description:
 */
public class ParseNestedInteger {

    public NestedInteger deserialize(String s) {
        if (s == null || s.trim().length() == 0) {
            return null;
        }

        Stack<Object> stack = new Stack<>();
        char ch;
        for (int i = s.length() - 1; i >= 0; i--) {
            ch = s.charAt(i);
            switch (ch) {
                case ',':
                    break;
                case ']':
                    stack.push(ch);
                    break;
                case '[': {
                    NestedInteger ni = new NestedInteger();
                    while (!stack.isEmpty()) {
                        Object top = stack.pop();
                        if (top instanceof Character) {
                            break;
                        } else {
                            ni.add((NestedInteger) top);
                        }
                    }
                    stack.push(ni);
                    break;
                }
                default: {
                    int end = i--;
                    while (i >= 0 && s.charAt(i) != ',' && s.charAt(i) != '[') {
                        i--;
                    }
                    stack.push(new NestedInteger(Integer.parseInt(s.substring(i + 1, end + 1))));
                    i++;
                    break;
                }
            }
        }

        return (NestedInteger) stack.pop();
    }


    static class NestedInteger {
        private Integer value;
        private List<NestedInteger> list;

        // Constructor initializes an empty nested list.
        public NestedInteger() {
            this.list = new ArrayList<>();
        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
            this.value = value;
            this.list = new ArrayList<>();

        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return value != null;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return value;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
            this.value = value;
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
            this.list.add(ni);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return list;
        }
    }

    public static void main(String[] args) {
        ParseNestedInteger parseNestedInteger = new ParseNestedInteger();
//        NestedInteger nestedInteger = parseNestedInteger.deserialize("[123,[-4,[5]],6]");
        NestedInteger nestedInteger = parseNestedInteger.deserialize("342");
        Assert.assertTrue(CollectionUtils.isNotEmpty(nestedInteger.list));
    }
}
