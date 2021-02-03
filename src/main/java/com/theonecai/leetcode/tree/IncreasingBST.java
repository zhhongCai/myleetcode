package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 897
 * @Author: theonecai
 * @Date: Create in 2021/2/3 20:47
 * @Description:
 */
public class IncreasingBST {
    private TreeNode node;
    private TreeNode next;

    public TreeNode increasingBST(TreeNode root) {
        node = new TreeNode();
        next = node;
        dfs(root);

        return node.right;
    }

    private void dfs(TreeNode n) {
        if (n == null) {
            return;
        }
        dfs(n.left);
        this.next.right = new TreeNode(n.val);
        this.next = this.next.right;
        dfs(n.right);
    }

    public static void main(String[] args) {
        IncreasingBST increasingBST = new IncreasingBST();
        Assert.assertEquals("[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]", BinaryTreeUtil.serialize(increasingBST.increasingBST(
                BinaryTreeUtil.deserialize("[5,3,6,2,4,null,8,1,null,null,null,7,9]")
        )));
    }
}
