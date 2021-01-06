package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 129
 */
public class SumNumbers {

    private int sum;

    public int sumNumbers(TreeNode root) {
        sum = 0;

        dfs(root, new ArrayList<>());

        return sum;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        if (node.left == null && node.right == null) {
            int b = 1;
            for (int i = list.size() - 1; i >= 0; i--) {
                sum += list.get(i) * b;
                b *= 10;
            }
            return;
        }

        if (node.left != null) {
            dfs(node.left, list);
            list.remove(list.size() - 1);
        }

        if (node.right != null) {
            dfs(node.right, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        SumNumbers sumNumbers = new SumNumbers();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        Assert.assertEquals(25, sumNumbers.sumNumbers(root));

        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        Assert.assertEquals(135 + 136 + 14, sumNumbers.sumNumbers(root));

        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(6);
        Assert.assertEquals(135 + 136 + 146 + 146, sumNumbers.sumNumbers(root));
    }
}
