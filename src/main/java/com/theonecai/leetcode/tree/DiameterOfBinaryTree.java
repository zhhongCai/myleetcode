package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 543
 */
public class DiameterOfBinaryTree {

    private int result;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        this.result = 0;

        dfs(root, 0);

        return this.result;
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }

        int left = 0;
        int right = 0;
        if (node.left != null) {
            left = dfs(node.left, depth + 1);
        }
        if (node.right != null) {
            right = dfs(node.right, depth + 1);
        }

        this.result = Math.max(result, left + right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();
        Assert.assertEquals(0, diameterOfBinaryTree.diameterOfBinaryTree(
                BinaryTreeUtil.deserialize("[1]")
        ));

        Assert.assertEquals(1, diameterOfBinaryTree.diameterOfBinaryTree(
                BinaryTreeUtil.deserialize("[1,2]")
        ));

        Assert.assertEquals(3, diameterOfBinaryTree.diameterOfBinaryTree(
                BinaryTreeUtil.deserialize("[1,2,3,4,5]")
        ));

        Assert.assertEquals(6, diameterOfBinaryTree.diameterOfBinaryTree(
                BinaryTreeUtil.deserialize("[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]")
        ));
    }
}
