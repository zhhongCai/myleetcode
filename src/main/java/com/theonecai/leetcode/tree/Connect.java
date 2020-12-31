package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 116
 */
public class Connect {

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null) {
            if (root.next != null) {
                Node parentNext = root.next;
                if (parentNext.left != null) {
                    root.right.next = parentNext.left;
                }
            }
        }
        connect(root.left);
        connect(root.right);

        return root;
    }

    public static void main(String[] args) {
        Connect connect = new Connect();
        Node root = new Node(10);
        root.left = new Node(6);
        root.left.left = new Node(5);
        root.left.right = new Node(7);
        root.right = new Node(15);
        root.right.left = new Node(11);
        root.right.right = new Node(17);
        connect.connect(root);
        Assert.assertNotNull(root);
    }
}
