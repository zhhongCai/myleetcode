package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 872
 * @Author: theonecai
 * @Date: Create in 2021/1/31 21:37
 * @Description:
 */
public class LeafSimilar {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        dfs(root1, list);
        dfs(root2, list2);

        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(list2.get(i))) {
                return false;
            }
        }

        return true;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        dfs(node.left, list);
        dfs(node.right, list);
        if (node.left == null && node.right == null) {
            list.add(node.val);
        }
    }

    public static void main(String[] args) {
        LeafSimilar similar = new LeafSimilar();
        Assert.assertTrue(similar.leafSimilar(BinaryTreeUtil.deserialize("[3,5,1,6,2,9,8,null,null,7,4]"),
                BinaryTreeUtil.deserialize("[3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]")));
        Assert.assertTrue(similar.leafSimilar(BinaryTreeUtil.deserialize("[1]"),
                BinaryTreeUtil.deserialize("[1]")));
        Assert.assertFalse(similar.leafSimilar(BinaryTreeUtil.deserialize("[1]"),
                BinaryTreeUtil.deserialize("[3]")));
        Assert.assertTrue(similar.leafSimilar(BinaryTreeUtil.deserialize("[1,2]"),
                BinaryTreeUtil.deserialize("[2,2]")));
    }
}
