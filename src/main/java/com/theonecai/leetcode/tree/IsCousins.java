package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 993
 * @Author: theonecai
 * @Date: Create in 2021/3/5 20:54
 * @Description:
 */
public class IsCousins {

    private TreeNode xParent;
    private TreeNode yParent;
    private int xLevel;
    private int yLevel;

    public boolean isCousins(TreeNode root, int x, int y) {
        xParent = root;
        yParent = root;
        xLevel = 0;
        yLevel = 0;

        dfs(root, root, x, y, 0);

        if (xParent.val == yParent.val) {
            return false;
        }

        return xLevel == yLevel;
    }

    private void dfs(TreeNode node, TreeNode parent, int x, int y, int level) {
        if (node == null) {
            return;
        }

        if (node.val == x) {
            xParent = parent;
            xLevel = level;
        }
        if (node.val == y) {
            yParent = parent;
            yLevel = level;
        }
        dfs(node.left, node, x, y, level + 1);
        dfs(node.right, node, x, y, level + 1);
    }

    public static void main(String[] args) {
        IsCousins isCousins = new IsCousins();
        Assert.assertFalse(isCousins.isCousins(BinaryTreeUtil.deserialize("[1,2,3,4]"), 3, 4));
        Assert.assertTrue(isCousins.isCousins(BinaryTreeUtil.deserialize("[1,2,3,null,4,null,5]"), 5, 4));
        Assert.assertFalse(isCousins.isCousins(BinaryTreeUtil.deserialize("[1,2,3,null,4]"), 2, 3));
    }
}
