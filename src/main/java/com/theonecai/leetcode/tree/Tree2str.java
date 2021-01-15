package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 *  606
 */
public class Tree2str {

    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        dfs(t, sb);

        return sb.toString();
    }

    private void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.val);

        if (node.left == null && node.right == null) {
            return;
        }

        sb.append("(");
        dfs(node.left, sb);
        sb.append(")");

        if (node.right != null) {
            sb.append("(");
            dfs(node.right, sb);
            sb.append(")");
        }

    }

    public static void main(String[] args) {
        Tree2str tree2str = new Tree2str();
        Assert.assertEquals("1", tree2str.tree2str(BinaryTreeUtil.deserialize("[1]")));
        Assert.assertEquals("1(2(4))(3)", tree2str.tree2str(BinaryTreeUtil.deserialize("[1,2,3,4]")));
        Assert.assertEquals("1(2()(4))(3)", tree2str.tree2str(BinaryTreeUtil.deserialize("[1,2,3,null,4]")));
    }
}
