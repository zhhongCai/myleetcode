package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 100
 */
public class IsSameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return visit(p, q);
    }

    private boolean visit(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        return visit(p.left, q.left) && visit(p.right, q.right);
    }

    public static void main(String[] args) {
        IsSameTree isSameTree = new IsSameTree();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        Assert.assertTrue(isSameTree.isSameTree(root, root));
    }
}
