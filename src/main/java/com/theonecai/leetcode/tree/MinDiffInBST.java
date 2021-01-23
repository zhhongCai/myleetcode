package com.theonecai.leetcode.tree;

/**
 * leetcode 783
 */
public class MinDiffInBST {
    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int minAbsValue = Integer.MAX_VALUE;

        if (root.left != null) {
            TreeNode leftMax = root.left;
            while (leftMax.right != null) {
                leftMax = leftMax.right;
            }
            minAbsValue = Math.min(minAbsValue, Math.abs(root.val - leftMax.val));
        }
        if (root.right != null) {
            TreeNode rightMin = root.right;
            while (rightMin.left != null) {
                rightMin = rightMin.left;
            }
            minAbsValue = Math.min(minAbsValue, Math.abs(root.val - rightMin.val));
        }
        minAbsValue = Math.min(minAbsValue, minDiffInBST(root.left));
        minAbsValue = Math.min(minAbsValue, minDiffInBST(root.right));

        return minAbsValue;
    }
}
