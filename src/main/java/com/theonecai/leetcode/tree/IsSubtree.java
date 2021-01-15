package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 572
 */
public class IsSubtree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (t == null) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameTree(s, t);
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (t == null || s == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    public static void main(String[] args) {
        IsSubtree isSubtree = new IsSubtree();
        Assert.assertTrue(isSubtree.isSubtree(BinaryTreeUtil.deserialize("[1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2]"),
                BinaryTreeUtil.deserialize("[1,null,1,null,1,null,1,null,1,null,1,2]")));

        Assert.assertFalse(isSubtree.isSubtree(BinaryTreeUtil.deserialize("[4,1,null,1,null,6,7]"),
                BinaryTreeUtil.deserialize("[4,1,null,6,7]")));

        Assert.assertFalse(isSubtree.isSubtree(BinaryTreeUtil.deserialize("[4,null,2]"),
                BinaryTreeUtil.deserialize("[3,4,5,null,2,null,2]")));

        Assert.assertTrue(isSubtree.isSubtree(BinaryTreeUtil.deserialize("[1,1]"),
                BinaryTreeUtil.deserialize("[1]")));

        Assert.assertTrue(isSubtree.isSubtree(BinaryTreeUtil.deserialize("[]"),
                BinaryTreeUtil.deserialize("[]")));

        Assert.assertTrue(isSubtree.isSubtree(BinaryTreeUtil.deserialize("[1]"),
                BinaryTreeUtil.deserialize("[1]")));

        Assert.assertTrue(isSubtree.isSubtree(BinaryTreeUtil.deserialize("[1]"),
                BinaryTreeUtil.deserialize("[]")));

        Assert.assertTrue(isSubtree.isSubtree(BinaryTreeUtil.deserialize("[1,2,3]"),
                BinaryTreeUtil.deserialize("[1,2,3]")));

        Assert.assertTrue(isSubtree.isSubtree(BinaryTreeUtil.deserialize("[3,4,5,1,2]"),
                BinaryTreeUtil.deserialize("[4,1,2]")));

        System.out.println();
        Assert.assertFalse(isSubtree.isSubtree(BinaryTreeUtil.deserialize("[3,4,5,1,2,null,null,0]"),
                BinaryTreeUtil.deserialize("[4,1,2]")));
    }
}
