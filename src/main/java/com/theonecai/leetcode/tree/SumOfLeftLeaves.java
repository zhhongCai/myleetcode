package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 404
 * @Author: theonecai
 * @Date: Create in 2021/1/9 09:26
 * @Description:
 */
public class SumOfLeftLeaves {
    private int sum;
    public int sumOfLeftLeaves(TreeNode root) {
        this.sum = 0;
        dfs(root);
        return this.sum;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            if (node.left.left == null && node.left.right == null) {
                this.sum += node.left.val;
            }
            dfs(node.left);
        }
        dfs(node.right);
    }

    public static void main(String[] args) {
        SumOfLeftLeaves sumOfLeftLeaves = new SumOfLeftLeaves();
        Assert.assertEquals(0, sumOfLeftLeaves.sumOfLeftLeaves(null));
        Assert.assertEquals(11, sumOfLeftLeaves.sumOfLeftLeaves(TreeNode.buildTree()));
    }

}
