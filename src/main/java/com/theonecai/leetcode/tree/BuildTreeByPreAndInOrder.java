package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 105
 * @Author: theonecai
 * @Date: Create in 2020/12/30 21:23
 * @Description:
 */
public class BuildTreeByPreAndInOrder {

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int inorderRootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                inorderRootIndex = i;
                break;
            }
        }

        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, inorderRootIndex + 1);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, inorderRootIndex);
        int[] rightPreorder = Arrays.copyOfRange(preorder, inorderRootIndex + 1, preorder.length);
        int[] rightInorder = Arrays.copyOfRange(inorder, inorderRootIndex + 1, inorder.length);
        TreeNode left = buildTree(leftPreorder, leftInorder);
        TreeNode right = buildTree(rightPreorder, rightInorder);
        root.left = left;
        root.right = right;
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int idx = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                idx = i;
                break;
            }
        }

        root.left = buildTree(preorder, inorder, 1, idx, 0, idx - 1);
        root.right = buildTree(preorder, inorder, idx + 1, preorder.length - 1, idx + 1, inorder.length - 1);
        return root;
    }

    /**
     *
     * @param preorder
     * @param inorder
     * @param preStart (包括)
     * @param preEnd (包括)
     * @param inStart (包括)
     * @param inEnd (包括)
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[preStart]);
        int idx = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (preorder[preStart] == inorder[i]) {
                idx = i;
                break;
            }
        }
        int leftLen = idx - inStart;
        if (preStart + 1 <= preStart + leftLen) {
            node.left = buildTree(preorder, inorder, preStart + 1, preStart + leftLen , inStart, idx - 1);
        }
        if (preStart + leftLen + 1 <= preEnd) {
            node.right = buildTree(preorder, inorder, preStart + leftLen + 1, preEnd , idx + 1, inEnd);
        }
        return node;
    }


    public static void main(String[] args) {
        BuildTreeByPreAndInOrder buildTree = new BuildTreeByPreAndInOrder();
        TreeNode root = buildTree.buildTree(new int[]{7, 3, 2, 5, 15, 9, 20}, new int[]{2, 3, 5, 7, 9, 15, 20});
        Assert.assertNotNull(root);
        root = buildTree.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        Assert.assertNotNull(root);
    }
}
