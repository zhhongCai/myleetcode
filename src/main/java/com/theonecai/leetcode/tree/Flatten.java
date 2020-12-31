package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 114
 */
public class Flatten {

    public void flatten(TreeNode root) {
        flattenTree(root);
    }

    /**
     * return head and tail node
     * @param node
     * @return
     */
    private TreeNode[] flattenTree(TreeNode node) {
        if (node == null) {
            return new TreeNode[2];
        }
        TreeNode head = node;
        TreeNode tail = node;
        if (node.left == null && node.right == null) {
            return new TreeNode[] {head, tail};
        }

        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left != null) {
            TreeNode[] leftList = flattenTree(left);
            node.left = null;
            node.right = leftList[0];

            TreeNode[] rightList = flattenTree(right);
            if (leftList[1] != null) {
                leftList[1].right = rightList[0];
            } else {
                node.right = rightList[0];
            }
            tail = rightList[1] == null ? leftList[1] : rightList[1];
        } else {
            TreeNode[] rightList = flattenTree(right);
            node.right = rightList[0];
            tail = rightList[1];
        }

        node.left = null;

        return new TreeNode[]{head, tail};
    }

    public static void main(String[] args) {
        // [1,2,null,3,4,5]
        Flatten flatten = new Flatten();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(5);
        root.left.right = new TreeNode(4);
        flatten.flatten(root);
        Assert.assertNotNull(root);

        root = TreeNode.buildTree();
        flatten.flatten(root);
        Assert.assertNotNull(root);
    }
}
