package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 671
 */
public class FindSecondMinimumValue {

    private long secondMin;
    private int min;

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        this.min = root.val;
        this.secondMin = Long.MAX_VALUE;

        dfs(root.left);
        dfs(root.right);

        return this.secondMin == Long.MAX_VALUE ? -1 : (int)this.secondMin;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.val != this.min) {
            this.secondMin = Math.min(this.secondMin, node.val);
        }
        dfs(node.left);
        dfs(node.right);
    }

    public static void main(String[] args) {
        FindSecondMinimumValue findSecondMinimumValue = new FindSecondMinimumValue();
        Assert.assertEquals(5, findSecondMinimumValue.findSecondMinimumValue(BinaryTreeUtil.deserialize("[2,2,5,null,null,5,7]")));
        Assert.assertEquals(-1, findSecondMinimumValue.findSecondMinimumValue(BinaryTreeUtil.deserialize("[2,2,2]")));
        Assert.assertEquals(3, findSecondMinimumValue.findSecondMinimumValue(BinaryTreeUtil.deserialize("[2,2,3]")));
    }
}
