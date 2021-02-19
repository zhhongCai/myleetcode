package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 965
 */
public class IsUnivalTree {

    public boolean isUnivalTree(TreeNode root) {
        return isUnivalTree(root.left, root.val) && isUnivalTree(root.right, root.val);
    }


    public boolean isUnivalTree(TreeNode node, int val) {
        if (node == null) {
            return true;
        }
        if (node.val != val) {
            return false;
        }
        return isUnivalTree(node.left, val) && isUnivalTree(node.right, val);
    }

    public static void main(String[] args) {
        IsUnivalTree isUnivalTree = new IsUnivalTree();
        Assert.assertTrue(isUnivalTree.isUnivalTree(BinaryTreeUtil.deserialize("[1,1,1,1,1]")));
        Assert.assertTrue(isUnivalTree.isUnivalTree(BinaryTreeUtil.deserialize("[1,1,1,1,1,null,1]")));
        Assert.assertFalse(isUnivalTree.isUnivalTree(BinaryTreeUtil.deserialize("[2,2,2,5,2]")));
    }
}
