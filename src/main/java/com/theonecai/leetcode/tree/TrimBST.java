package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 669
 */
public class TrimBST {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (high < root.val) {
            return trimBST(root.left, low, high);
        }
        if (high == root.val || low <= root.val) {
            return deleteOutOffRange(root, low, high);
        }
        return trimBST(root.right, low, high);
    }

    private TreeNode deleteOutOffRange(TreeNode node, int low, int high) {
        if (node == null) {
            return null;
        }
        if (node.val < low) {
            return deleteOutOffRange(node.right, low, high);
        } else if (node.val > high) {
            return deleteOutOffRange(node.left, low, high);
        } else {
            node.left = deleteOutOffRange(node.left, low, high);
            node.right = deleteOutOffRange(node.right, low, high);
        }

        return node;
    }

    public static void main(String[] args) {
        TrimBST trimBST = new TrimBST();
        Assert.assertEquals("[1,null,2]", BinaryTreeUtil.serialize(trimBST.trimBST(
                BinaryTreeUtil.deserialize("[1,0,2]"), 1, 2)));
        Assert.assertEquals("[3,2,null,1]", BinaryTreeUtil.serialize(trimBST.trimBST(
                BinaryTreeUtil.deserialize("[3,0,4,null,2,null,null,1]"), 1, 3)));
        Assert.assertEquals("[1]", BinaryTreeUtil.serialize(trimBST.trimBST(
                BinaryTreeUtil.deserialize("[1]"), 1, 2)));
        Assert.assertEquals("[1,null,2]", BinaryTreeUtil.serialize(trimBST.trimBST(
                BinaryTreeUtil.deserialize("[1,null,2]"), 1, 3)));
        Assert.assertEquals("[2]", BinaryTreeUtil.serialize(trimBST.trimBST(
                BinaryTreeUtil.deserialize("[1,null,2]"), 2, 4)));
        Assert.assertEquals("[]", BinaryTreeUtil.serialize(trimBST.trimBST(
                BinaryTreeUtil.deserialize("[1,null,2]"), 3, 4)));
    }
}
