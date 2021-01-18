package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 590 ,589
 * @Author: theonecai
 * @Date: Create in 2021/1/17 15:17
 * @Description:
 */
public class Postorder {

    public List<Integer> postorder(Node root) {
        List<Integer> result = new LinkedList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (node.children != null) {
            for (Node child : node.children) {
                postorder(child, result);
            }
        }
        result.add(node.val);
    }

    public List<Integer> preorder(Node root) {
        List<Integer> result = new LinkedList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        if (node.children != null) {
            for (Node child : node.children) {
                preorder(child, result);
            }
        }
    }

    public static void main(String[] args) {
        Postorder postorder = new Postorder();
        Node root = new Node(3);
        root.children = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            root.children.add(new Node(i));
        }
        root.children.get(0).children = new ArrayList<>();
        root.children.get(0).children.add(new Node(3));
        System.out.println(postorder.postorder(root));
        System.out.println(postorder.preorder(root));
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
