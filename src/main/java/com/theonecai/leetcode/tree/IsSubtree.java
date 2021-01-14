package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 572
 */
public class IsSubtree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return true;
        }
        return dfs(s, t, false) || dfs(t, s, false);
    }

    private boolean dfs(TreeNode s, TreeNode t, boolean parentEq) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }

        if (s.val != t.val) {
            return !parentEq && (dfs(s.left, t, false) || dfs(s.right, t, false));
        }

        boolean result = dfs(s.left, t.left, true) && dfs(s.right, t.right, true);
        if (result) {
            return true;
        }

        return dfs(s.left, t, false) || dfs(s.right, t, false);
    }

    public static void main(String[] args) {
        IsSubtree isSubtree = new IsSubtree();
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
