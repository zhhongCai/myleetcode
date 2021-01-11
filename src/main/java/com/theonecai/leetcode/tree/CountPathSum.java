package com.theonecai.leetcode.tree;

import com.theonecai.algorithms.ArrayUtil;
import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 437
 */
public class CountPathSum {

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int count = 0;

        count += dfs(root, sum);

        count += pathSum(root.left, sum);
        count += pathSum(root.right, sum);

        return count;
    }

    /**
     * 以node节点开始的路径和的数量
     * @param node
     * @param node
     * @return
     */
    private int dfs(TreeNode node, int sum) {
        if (node == null) {
            return  0;
        }
        int count = 0;
        if (node.val == sum) {
            count++;
        }
        if (node.left != null) {
            count += dfs(node.left, sum - node.val);
        }
        if (node.right != null) {
            count += dfs(node.right, sum - node.val);
        }
        return count;
    }

    public static void main(String[] args) {
        CountPathSum countPathSum = new CountPathSum();
        TreeNode root = TreeNode.buildTree();
        System.out.println(BinaryTreeUtil.serialize(root));
        Assert.assertEquals(1, countPathSum.pathSum(root, 10));
        int[] arr = ArrayUtil.randIntArray(1000);
        TreeNode rr = BinaryTreeUtil.deserialize(Arrays.toString(arr));
        RunUtil.runAndPrintCostTime(() -> {
            Assert.assertEquals(0, countPathSum.pathSum(rr, 12312));
        });
        root = BinaryTreeUtil.deserialize("[10,5,-3,3,2,null,11,3,-2,null,1]");
        Assert.assertEquals(3, countPathSum.pathSum(root, 8));
    }
}
