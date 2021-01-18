package com.theonecai.leetcode.tree;

/**
 * leetcode 623
 * @Author: theonecai
 * @Date: Create in 2021/1/17 20:46
 * @Description:
 */
public class AddOneRow {

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode n = new TreeNode(v);
            n.left = root;
            return n;
        }

        dfs(root, v, 1, d);

        return root;
    }

    private void dfs(TreeNode node, int v, int depth, int d) {
        if (node == null || depth >= d) {
            return;
        }
        if (depth == d - 1) {
            TreeNode left = new TreeNode(v);
            left.left = node.left;
            node.left = left;

            TreeNode right = new TreeNode(v);
            right.right = node.right;
            node.right = right;

            return;
        }
        dfs(node.left, v, depth + 1, d);
        dfs(node.right, v, depth + 1, d);
    }

    public static void main(String[] args) {
        AddOneRow addOneRow = new AddOneRow();
        TreeNode root = addOneRow.addOneRow(BinaryTreeUtil.deserialize("[4,2,6,3,1,5]"), 1, 2);
        System.out.println(BinaryTreeUtil.serialize(root));

        root = addOneRow.addOneRow(BinaryTreeUtil.deserialize("[4,2,null,3,1]"), 1, 3);
        System.out.println(BinaryTreeUtil.serialize(root));
    }
}
