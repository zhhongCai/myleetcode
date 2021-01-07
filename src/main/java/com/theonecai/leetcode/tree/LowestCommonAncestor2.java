package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 236
 */
public class LowestCommonAncestor2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p.val == q.val) {
            return p;
        }
        if (p.val == root.val || q.val == root.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        return root;
    }

    public static void main(String[] args) {
        LowestCommonAncestor2 lowestCommonAncestor = new LowestCommonAncestor2();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        Assert.assertEquals(3, lowestCommonAncestor.lowestCommonAncestor(root, p, q).val);
        q = new TreeNode(4);
        Assert.assertEquals(5, lowestCommonAncestor.lowestCommonAncestor(root, p, q).val);
        p = new TreeNode(8);
        q = new TreeNode(7);
        Assert.assertEquals(3, lowestCommonAncestor.lowestCommonAncestor(root, p, q).val);
    }
}
