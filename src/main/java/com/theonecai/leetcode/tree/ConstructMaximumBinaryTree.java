package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 654
 */
public class ConstructMaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (end < start) {
            return null;
        }
        if (end == start) {
            return new TreeNode(nums[start]);
        }
        int maxIndex = start;
        for (int i = start; i <= end; i++) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = constructMaximumBinaryTree(nums, start, maxIndex - 1);
        node.right = constructMaximumBinaryTree(nums, maxIndex + 1, end);

        return node;
    }

    public static void main(String[] args) {
        ConstructMaximumBinaryTree tree = new ConstructMaximumBinaryTree();
        Assert.assertEquals("[6,3,5,null,2,0,null,null,1]",
                BinaryTreeUtil.serialize(tree.constructMaximumBinaryTree(new int[]{3,2,1,6,0,5})));
    }
}
