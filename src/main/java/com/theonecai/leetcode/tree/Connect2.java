package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 117
 */
public class Connect2 {

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.addLast(node.left);
                    node.left.next = node.right == null ? getParentNextChild(node) : node.right;
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                    node.right.next = getParentNextChild(node);
                }
                size--;
            }

        }

        return root;
    }

    private Node getParentNextChild(Node parent) {
        if (parent == null || parent.next == null) {
            return  null;
        }
        Node parentNext = parent.next;
        while (parentNext.left == null && parentNext.right == null && parentNext.next != null) {
            parentNext = parentNext.next;
        }
        return parentNext.left != null ? parentNext.left : parentNext.right;
    }

    public static void main(String[] args) {
        Connect2 connect = new Connect2();
        Node root = new Node(2);
        root.left = new Node(1);
        root.left.left = new Node(0);
        root.left.left.left = new Node(2);
        root.left.right = new Node(7);
        root.left.right.left = new Node(1);
        root.left.right.right = new Node(0);
        root.left.right.right.left = new Node(7);
        root.right = new Node(3);
        root.right.left = new Node(9);
        root.right.right = new Node(1);
        root.right.right.left = new Node(8);
        root.right.right.right = new Node(8);
        connect.connect(root);

        root = new Node(10);
        root.left = new Node(6);
        root.left.left = new Node(5);
        root.left.right = new Node(7);
        root.right = new Node(15);
//        root.right.left = new Node(11);
        root.right.right = new Node(17);
        connect.connect(root);
        Assert.assertNotNull(root);
    }
}
