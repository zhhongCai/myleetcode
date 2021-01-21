package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 653
 */
public class FindTarget {

    /**
     * n*log(n)
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root, k);
    }

    private boolean dfs(TreeNode root, TreeNode node, int k) {
        if (node == null) {
            return false;
        }
        if (k % 2 == 0 && (k - node.val == k / 2)) {
            return dfs(root, node.left, k) || dfs(root, node.right, k);
        }
        boolean result = binarySearch(root, k - node.val);
        if (result) {
            return true;
        }
        return dfs(root, node.left, k) || dfs(root, node.right, k);
    }

    public boolean binarySearch(TreeNode root, int value) {
        TreeNode node = root;
        while (node != null) {
            if (node.val == value) {
                return true;
            }
            if (node.val > value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FindTarget findTarget = new FindTarget();
        Assert.assertFalse(findTarget.findTarget(BinaryTreeUtil.deserialize("[1]"), 2));
        Assert.assertTrue(findTarget.findTarget(BinaryTreeUtil.deserialize("[2,1,3]"), 4));
        Assert.assertTrue(findTarget.findTarget(BinaryTreeUtil.deserialize("[5,3,6,2,4,null,7]"), 9));
        Assert.assertFalse(findTarget.findTarget(BinaryTreeUtil.deserialize("[5,3,6,2,4,null,7]"), 28));
    }
}
