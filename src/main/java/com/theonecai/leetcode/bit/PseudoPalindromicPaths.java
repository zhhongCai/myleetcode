package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * 1457
 */
public class PseudoPalindromicPaths {

    private int count;
    public int pseudoPalindromicPaths (TreeNode root) {
        count = 0;

        dfs(root, new int[10]);

        return count;
    }

    private void dfs(TreeNode node, int[] valCount) {
        if (node == null) {
            return;
        }
        valCount[node.val]++;
        if (node.left == null && node.right == null) {
            if (pseudoPalindromic(valCount)) {
                count++;
            }
            valCount[node.val]--;
            return;
        }
        if (node.left != null) {
            dfs(node.left, valCount);
        }
        if (node.right != null) {
            dfs(node.right, valCount);
        }
        valCount[node.val]--;
    }

    private boolean pseudoPalindromic(int[] valCount) {
        int total = 0;
        int oddCount = 0;
        for (Integer count : valCount) {
            total += count;
            if ((count & 1) != 0) {
                oddCount++;
            }
        }
        return ((total & 1) == 1) && oddCount == 1;
    }


    public static void main(String[] args) {
        PseudoPalindromicPaths pseudoPalindromicPaths = new PseudoPalindromicPaths();
        int[] nums = {9,5,4,5,-1,2,6,2,5,-1,8,3,9,2,3,1,1,-1,4,5,4,2,2,6,4,-1,-1,1,7,-1,5,4,7,-1,-1,7,-1,1,5,6,1,-1,-1,-1,-1,9,2,-1,9,7,2,1,
            -1,-1,-1,6,-1,-1,-1,-1,-1,-1,-1,-1,-1,5,-1,-1,3,-1,-1,-1,8,-1,1,-1,-1,8,-1,-1,-1,-1,2,-1,8,7};
        TreeNode root = buildTree(nums);
        Assert.assertEquals(1, pseudoPalindromicPaths.pseudoPalindromicPaths(root));

        root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        Assert.assertEquals(2, pseudoPalindromicPaths.pseudoPalindromicPaths(root));

        root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.left.right.right = new TreeNode(1);
        root.right = new TreeNode(1);
        Assert.assertEquals(1, pseudoPalindromicPaths.pseudoPalindromicPaths(root));

        root = new TreeNode(9);
        Assert.assertEquals(1, pseudoPalindromicPaths.pseudoPalindromicPaths(root));
    }

    private static TreeNode buildTree(int[] nums) {
        return null;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
