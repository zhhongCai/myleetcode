package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 106
 */
public class BuildTreeByInAndPostorder {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length < 1) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        int idx = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (postorder[postorder.length - 1] == inorder[i]) {
                idx = i;
                break;
            }
        }
        root.left = buildTree(inorder, postorder, 0, idx - 1, 0, idx - 1);
        root.right = buildTree(inorder, postorder, idx + 1, inorder.length - 1, idx, postorder.length - 2);
        return root;
    }

    /**
     *
     * @param inorder
     * @param postorder
     * @param inStart (包括)
     * @param inEnd (包括)
     * @param pStart (包括)
     * @param pEnd (包括)
     * @return
     */
    private TreeNode buildTree(int[] inorder, int[] postorder, int inStart, int inEnd, int pStart, int pEnd) {
        if (pStart > pEnd) {
            return null;
        }
        TreeNode node = new TreeNode(postorder[pEnd]);
        int idx = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == postorder[pEnd]) {
                idx = i;
                break;
            }
        }

        int leftLen = idx - inStart;
        if (inStart <= idx - 1) {
            node.left = buildTree(inorder, postorder, inStart, idx -1, pStart, pStart + leftLen - 1);
        }
        if (idx + 1 <= inEnd) {
            node.right = buildTree(inorder, postorder, idx + 1, inEnd, pStart + leftLen, pEnd - 1);
        }

        return node;
    }

    public static void main(String[] args) {
        BuildTreeByInAndPostorder buildTreeByInAndPostorder = new BuildTreeByInAndPostorder();
        TreeNode root = buildTreeByInAndPostorder.buildTree(new int[]{2, 3, 5, 7, 9, 15, 20}, new int[]{2, 5, 3, 9, 20, 15, 7});
        Assert.assertNotNull(root);
    }
}
