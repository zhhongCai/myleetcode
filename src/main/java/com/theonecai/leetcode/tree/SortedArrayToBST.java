package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 108
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (end + start) / 2;
        // 1 2 3 4 5 6
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, start, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, end);

        return node;
    }

    public static void main(String[] args) {
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        TreeNode root = sortedArrayToBST.sortedArrayToBST(new int[]{1,2,3,4,5,6});
        Assert.assertNotNull(root);
        root = sortedArrayToBST.sortedArrayToBST(new int[]{1,2,3,4,5});
        Assert.assertNotNull(root);
    }
}
