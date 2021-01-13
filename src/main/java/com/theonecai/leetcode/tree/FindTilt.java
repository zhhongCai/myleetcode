package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 563
 */
public class FindTilt {

    private int result;

    public int findTilt(TreeNode root) {
        result = 0;

        dfs(root);

        return this.result;
    }

    /**
     * 返回node为根的所有节点值的和
     * @param node
     * @return
     */
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int rightSum = dfs(node.right);

        int leftSum = dfs(node.left);

        this.result += Math.abs(rightSum - leftSum);

        return node.val + rightSum + leftSum;
    }

    public static void main(String[] args) {
        FindTilt findTilt = new FindTilt();
        Assert.assertEquals(1, findTilt.findTilt(BinaryTreeUtil.deserialize("[1,2,3]")));
        Assert.assertEquals(15, findTilt.findTilt(BinaryTreeUtil.deserialize("[4,2,9,3,5,null,7]")));
        Assert.assertEquals(9, findTilt.findTilt(BinaryTreeUtil.deserialize("[21,7,14,1,1,2,2,3,3]")));
    }

}
