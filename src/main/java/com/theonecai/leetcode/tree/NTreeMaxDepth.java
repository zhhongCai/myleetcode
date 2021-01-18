package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 589
 * @Author: theonecai
 * @Date: Create in 2021/1/17 14:15
 * @Description:
 */
public class NTreeMaxDepth {

    private int depth;

    public int maxDepth(Node root) {
        depth = 0;
        visit(root, 1);
        return depth;
    }

    private void visit(Node node, int d) {
        if (node == null) {
            return;
        }
        this.depth = Math.max(this.depth, d);
        if (node.children != null) {
            for (Node child : node.children) {
                visit(child, d + 1);
            }
        }
    }

    public static void main(String[] args) {
        NTreeMaxDepth maxDepth = new NTreeMaxDepth();
        Node root = new Node(3);
        root.children = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            root.children.add(new Node(i));
        }
        root.children.get(0).children = new ArrayList<>();
        root.children.get(0).children.add(new Node(3));

        Assert.assertEquals(3, maxDepth.maxDepth(root));
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
