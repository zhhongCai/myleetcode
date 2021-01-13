package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 530
 */
public class GetMinimumDifference {

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int minAbsValue = Integer.MAX_VALUE;

        if (root.left != null) {
            minAbsValue = Math.min(minAbsValue, Math.abs(root.val - root.left.val));
        }
        if (root.right != null) {
            minAbsValue = Math.min(minAbsValue, Math.abs(root.val - root.right.val));
        }
        minAbsValue = Math.min(minAbsValue, getMinimumDifference(root.left));
        minAbsValue = Math.min(minAbsValue, getMinimumDifference(root.right));

        return minAbsValue;
    }

    public static void main(String[] args) {
        GetMinimumDifference getMinimumDifference = new GetMinimumDifference();
        TreeNode root = BinaryTreeUtil.deserialize("[2,1,3]");
        Assert.assertEquals(1, getMinimumDifference.getMinimumDifference(root));

        root = BinaryTreeUtil.deserialize("[10,5,15,3,6,12,20]");
        Assert.assertEquals(1, getMinimumDifference.getMinimumDifference(root));
    }

}
