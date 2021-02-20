package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 979
 * @Author: theonecai
 * @Date: Create in 2021/2/20 21:40
 * @Description:
 */
public class DistributeCoins {

    private int result;

    public int distributeCoins(TreeNode root) {
        this.result = 0;
        postOrder(root);
        return this.result;
    }

    private int postOrder(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = postOrder(node.left);
        int right = postOrder(node.right);
        this.result += Math.abs(left) + Math.abs(right);
        return left + right + node.val - 1;
    }

    public static void main(String[] args) {
        DistributeCoins distributeCoins = new DistributeCoins();
        Assert.assertEquals(2, distributeCoins.distributeCoins(BinaryTreeUtil.deserialize("[3,0,0]")));
        Assert.assertEquals(3, distributeCoins.distributeCoins(BinaryTreeUtil.deserialize("[0,3,0]")));
        Assert.assertEquals(2, distributeCoins.distributeCoins(BinaryTreeUtil.deserialize("[1,0,2]")));
        Assert.assertEquals(4, distributeCoins.distributeCoins(BinaryTreeUtil.deserialize("[1,0,0,null,3]")));
    }
}
