package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 1026
 * @Author: theonecai
 * @Date: Create in 2021/3/7 22:07
 * @Description:
 */
public class MaxAncestorDiff {
    private int maxDiff;

    public int maxAncestorDiff(TreeNode root) {
        maxDiff = Integer.MIN_VALUE;

        postOrder(root);

        return maxDiff;
    }

    /**
     * 返回node中最大最小值
     * @param node
     * @return : int[0] - 最小， int[1] - 最大
     */
    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        if (left[0] != Integer.MAX_VALUE) {
            min = Math.min(min, left[0]);
            max = Math.max(max, left[0]);
        }
        if (left[1] != Integer.MIN_VALUE) {
            min = Math.min(min, left[1]);
            max = Math.max(max, left[1]);
        }
        if (right[0] != Integer.MAX_VALUE) {
            min = Math.min(min, right[0]);
            max = Math.max(max, right[0]);
        }
        if (right[1] != Integer.MIN_VALUE) {
            min = Math.min(min, right[1]);
            max = Math.max(max, right[1]);
        }

        if (min != Integer.MAX_VALUE) {
            maxDiff = Math.max(maxDiff, Math.abs(node.val - min));
        }
        if (max != Integer.MIN_VALUE) {
            maxDiff = Math.max(maxDiff, Math.abs(node.val - max));
        }
        min = Math.min(min, node.val);
        max = Math.max(max, node.val);

        return new int[] {min, max};
    }

    public static void main(String[] args) {
        MaxAncestorDiff diff = new MaxAncestorDiff();
        Assert.assertEquals(7, diff.maxAncestorDiff(BinaryTreeUtil.deserialize("[8,3,10,1,6,null,14,null,null,4,7,13]")));
        Assert.assertEquals(3, diff.maxAncestorDiff(BinaryTreeUtil.deserialize("[1,null,2,null,0,3]")));
    }
}
