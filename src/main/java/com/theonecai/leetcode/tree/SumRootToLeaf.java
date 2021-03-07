package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.List;

/**
 * leetcode 1022
 * @Author: theonecai
 * @Date: Create in 2021/3/7 20:30
 * @Description:
 */
public class SumRootToLeaf {

    private int sum;

    public int sumRootToLeaf(TreeNode root) {
        sum = 0;

//        dfs(root, new ArrayList<>());
        dfs(root, 0);

        return sum;
    }

    private void dfs(TreeNode node, int s) {
        if (node == null) {
            return;
        }
        s = (s << 1) + node.val;
        if (node.left == null && node.right == null) {
            sum += s;
            return;
        }

        dfs(node.left, s);
        dfs(node.right, s);
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        if (node.left == null && node.right == null) {
            sum += calc(list);
        }

        dfs(node.left, list);
        dfs(node.right, list);

        list.remove(list.size() - 1);
    }

    private int calc(List<Integer> list) {
        int f = 0;
        int num = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            num += (list.get(i) << f);
            f++;
        }
        return num;
    }

    public static void main(String[] args) {
        SumRootToLeaf sumRootToLeaf = new SumRootToLeaf();
        Assert.assertEquals(22, sumRootToLeaf.sumRootToLeaf(
                BinaryTreeUtil.deserialize("[1,0,1,0,1,0,1]")));
        Assert.assertEquals(0, sumRootToLeaf.sumRootToLeaf(
                BinaryTreeUtil.deserialize("[0]")));
        Assert.assertEquals(1, sumRootToLeaf.sumRootToLeaf(
                BinaryTreeUtil.deserialize("[1]")));
        Assert.assertEquals(3, sumRootToLeaf.sumRootToLeaf(
                BinaryTreeUtil.deserialize("[1,1]")));
    }
}
