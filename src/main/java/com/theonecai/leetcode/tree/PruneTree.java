package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 814
 */
public class PruneTree {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (allZero(root)) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        return root;
    }

    public boolean allZero(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (node.val == 1) {
            return false;
        }

        return allZero(node.left) && allZero(node.right);
    }

    public static void main(String[] args) {
        PruneTree pruneTree = new PruneTree();
        Assert.assertEquals("[]", BinaryTreeUtil.serialize(pruneTree.pruneTree(
                BinaryTreeUtil.deserialize("[0,0,0]"))));
        Assert.assertEquals("[1,null,0,null,1]", BinaryTreeUtil.serialize(pruneTree.pruneTree(
                BinaryTreeUtil.deserialize("[1,null,0,0,1]"))));
        Assert.assertEquals("[1,null,1,null,1]", BinaryTreeUtil.serialize(pruneTree.pruneTree(
                BinaryTreeUtil.deserialize("[1,0,1,0,0,0,1]"))));
        Assert.assertEquals("[1,1,0,1,1,null,1]", BinaryTreeUtil.serialize(pruneTree.pruneTree(
                BinaryTreeUtil.deserialize("[1,1,0,1,1,0,1,0]"))));
    }
}
