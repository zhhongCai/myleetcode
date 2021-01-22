package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 662
 */
public class WidthOfBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 1));
        int width = 1;
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int second = -1;
            int first = -1;
            int j = 0;
            while (j < size) {
                Node n = queue.poll();
                TreeNode node = n.node;
                if (node != null) {
                    queue.offer(new Node(node.left, n.position * 2));
                    queue.offer(new Node(node.right, n.position * 2 + 1));
                    if (first == -1) {
                        first = n.position;
                    }
                    second = n.position;
                }
                j++;
            }
            width = Math.max(width, (second - first) + 1);
            level++;
        }

        return width;
    }

    private static class Node {
        private TreeNode node;
        private int position;

        public Node(TreeNode node, int position) {
            this.node = node;
            this.position = position;
        }
    }

    public static void main(String[] args) {
        WidthOfBinaryTree treeWidth = new WidthOfBinaryTree();
        Assert.assertEquals(8,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[1,1,1,1,null,null,1,1,null,null,1]")));
        Assert.assertEquals(2,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[0,0,0,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null,null,0,0,null]")));
        Assert.assertEquals(3,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[1,2,3,null,5,null,7,8]")));
        Assert.assertEquals(8,  treeWidth.widthOfBinaryTree(
                BinaryTreeUtil.deserialize("[1,1,1,1,1,1,1,null,null,null,1,null,null,null,null,2,2,2,2,2,2,2,null,2,null,null,2,null,2]")));
        Assert.assertEquals(2,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[1,2,3,4]")));
        Assert.assertEquals(4,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[1,2,3,4,5,6,7,8]")));
        Assert.assertEquals(1,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[1,null,3,null,5,null,7,null,8]")));
    }
}
