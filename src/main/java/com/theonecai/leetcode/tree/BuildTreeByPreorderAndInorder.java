package com.theonecai.leetcode.tree;

import java.util.Arrays;

/**
 * 105
 */
public class BuildTreeByPreorderAndInorder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode(preorder[0]);
        int inorderRootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                inorderRootIndex = i;
                break;
            }
        }
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, inorderRootIndex + 1);
        int[] rightPreorder = Arrays.copyOfRange(preorder, 1, inorderRootIndex + 1)
        return root;
    }

    public static void main(String[] args) {
        BuildTreeByPreorderAndInorder buildTreeByPreorderAndInorder = new BuildTreeByPreorderAndInorder();
        int[] preorder = {7, 3, 4, 5, 15, 9, 20};
        int[] inorder = {4, 3, 5, 7, 9, 15, 20};
        TreeNode root = buildTreeByPreorderAndInorder.buildTree(preorder, inorder);
    }
}
