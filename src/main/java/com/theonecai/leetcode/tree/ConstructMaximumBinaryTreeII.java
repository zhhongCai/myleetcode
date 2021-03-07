package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 998
 */
public class ConstructMaximumBinaryTreeII {

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (val > root.val) {
            node.left = root;
            return node;
        }

        if (root.right == null) {
            root.right = node;
            return root;
        }
        if (root.right.val > val) {
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        } else {
            node.left = root.right;
            root.right = node;
        }
        return root;
    }


    public static void main(String[] args) {
        ConstructMaximumBinaryTreeII tree = new ConstructMaximumBinaryTreeII();
        Assert.assertEquals("[5,4,null,1,3,null,null,2]",
                BinaryTreeUtil.serialize(tree.insertIntoMaxTree(
                        BinaryTreeUtil.deserialize("[4,1,3,null,null,2]"), 5)));
        Assert.assertEquals("[5,2,4,null,1,null,3]",
                BinaryTreeUtil.serialize(tree.insertIntoMaxTree(
                        BinaryTreeUtil.deserialize("[5,2,4,null,1]"), 3)));
        Assert.assertEquals("[5,2,4,null,1,3]",
                BinaryTreeUtil.serialize(tree.insertIntoMaxTree(
                        BinaryTreeUtil.deserialize("[5,2,3,null,1]"), 4)));
    }
}
