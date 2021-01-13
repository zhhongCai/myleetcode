package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 543
 */
public class DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return dfs(root.left, 0) + dfs(root.right, 0);
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }

        return Math.max(dfs(node.left, depth + 1), dfs(node.right, depth + 1));
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
