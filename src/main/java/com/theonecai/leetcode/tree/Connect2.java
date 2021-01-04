package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 117
 */
public class Connect2 {

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            root.left.next = root.right == null ? getParentNextChild(root) : root.right;
        }
        if (root.right != null) {
            root.right.next = getParentNextChild(root);
        }
        connect(root.left);
        connect(root.right);

        return root;
    }

    private Node getParentNextChild(Node parent) {
        if (parent == null || parent.next == null) {
            return  null;
        }
        Node parentNext = parent.next;
        return parentNext.left != null ? parentNext.left : parentNext.right;
    }

    public static void main(String[] args) {
        Connect2 connect = new Connect2();
        Node root = new Node(10);
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
