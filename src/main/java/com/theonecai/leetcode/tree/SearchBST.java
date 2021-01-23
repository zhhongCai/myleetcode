package com.theonecai.leetcode.tree;

/**
 * leetcode 700
 */
public class SearchBST {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        TreeNode node = root;
        while (node != null && node.val != val) {
            if (node.val > val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    public static void main(String[] args) {
        SearchBST searchBST = new SearchBST();
        System.out.println(BinaryTreeUtil.serialize(searchBST.searchBST(BinaryTreeUtil.deserialize("[4,2,7,1,3]"), 2)));
        System.out.println(BinaryTreeUtil.serialize(searchBST.searchBST(BinaryTreeUtil.deserialize("[4,2,7,1,3]"), 5)));
    }
}
