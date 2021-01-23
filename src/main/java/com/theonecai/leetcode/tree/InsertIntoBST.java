package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 701
 */
public class InsertIntoBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            return newNode;
        }
        TreeNode node = root;
        TreeNode pre = null;
        while (node != null && node.val != val) {
            pre = node;
            if (node.val > val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (pre.val > val) {
            pre.left = newNode;
        } else {
            pre.right = newNode;
        }

        return root;
    }

    public static void main(String[] args) {
        InsertIntoBST insertIntoBST = new InsertIntoBST();
        Assert.assertEquals("[4,2,7,1,3,5]", BinaryTreeUtil.serialize(insertIntoBST.insertIntoBST(
                BinaryTreeUtil.deserialize("[4,2,7,1,3]"), 5
        )));

        Assert.assertEquals("[40,20,60,10,30,50,70,null,null,25]", BinaryTreeUtil.serialize(insertIntoBST.insertIntoBST(
                BinaryTreeUtil.deserialize("[40,20,60,10,30,50,70]"), 25
        )));
        Assert.assertEquals("[4,2,7,1,3,5]", BinaryTreeUtil.serialize(insertIntoBST.insertIntoBST(
                BinaryTreeUtil.deserialize("[4,2,7,1,3,null,null,null,null,null,null]"), 5
        )));
    }
}
