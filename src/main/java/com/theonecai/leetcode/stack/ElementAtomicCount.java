package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * leetcode 762
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/4 17:39
 * @Description:
 */
public class ElementAtomicCount {

    public String countOfAtoms(String formula) {
        if (formula == null || formula.length() == 0) {
            return "";
        }
        // sorted by key
        Map<String, Integer> map = new TreeMap<>();
        Stack<Node> stack = new Stack<>();
        char ch;
        for (int i = 0; i < formula.length(); i++) {
            ch = formula.charAt(i);
            if (isUppercaseAlpha(ch)) {
                String elem = nextElement(formula, i);
                i += elem.length();
                String numStr = nextNum(formula, i);
                i += numStr.length() - 2;
                stack.push(new Node(elem, Integer.parseInt(numStr)));
            } else if (ch == '(') {
                stack.push(new Node("(", 0));
            } else if (ch == ')') {
                String numStr = nextNum(formula, i + 1);
                i += numStr.length() - 1;
                int num = Integer.parseInt(numStr);

                List<Node> list = new ArrayList<>();
                while (!stack.isEmpty()) {
                    Node node = stack.pop();
                    if (node.getElement().equals("(")) {
                        break;
                    }
                    node.setCount(node.getCount() * num);
                    list.add(node);
                }
                stack.addAll(list);
            }
        }

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            String key = node.element;
            if (!map.containsKey(key)) {
                map.put(key, node.count);
            } else {
                map.put(key, map.get(key) + node.count);
            }
        }

        StringBuilder sb = new StringBuilder();
        map.forEach((k, v) -> {
            sb.append(k);
            if (v > 1) {
                sb.append(v);
            }
        });
        return sb.toString();
    }

    private String nextElement(String formula, int start) {
        int i = start + 1;
        while (i < formula.length() && isLowercaseAlpha(formula.charAt(i))) {
            i++;
        }
        return formula.substring(start, i);
    }

    private String nextNum(String formula, int start) {
        int i = start;
        if (i >= formula.length()) {
            return "1";
        }
        while (i < formula.length() && isDigital(formula.charAt(i))) {
            i++;
        }
        if (isDigital(formula.charAt(start))) {
            return formula.substring(start, i);
        }
        return "1";
    }

    private boolean isDigital(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean isUppercaseAlpha(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    private boolean isLowercaseAlpha(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    static class Node {
        private String element;
        private int count;

        public Node(String element, int count) {
            this.element = element;
            this.count = count;
        }

        public String getElement() {
            return element;
        }

        public void setElement(String element) {
            this.element = element;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static void main(String[] args) {
        ElementAtomicCount elementAtomicCount = new ElementAtomicCount();
        Assert.assertEquals("C2H9JMg2O7W2", elementAtomicCount.countOfAtoms("Mg2(H2O)4C2(W2J)O3H"));
        Assert.assertEquals("K4N2O14S4", elementAtomicCount.countOfAtoms("K4(ON(SO3)2)2"));
        Assert.assertEquals("Be32", elementAtomicCount.countOfAtoms("Be32"));
    }
}
