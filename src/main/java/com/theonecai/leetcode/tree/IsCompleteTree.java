package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 958
 */
public class IsCompleteTree {
    private List<List<TreeNode>> levels;

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        levels = new ArrayList<>();
        dfs(root, 0);
        int count = 1;
        for (int i = 0; i < levels.size() - 1; i++) {
            List<TreeNode> list = levels.get(i);
            if (list.size() != count) {
                return false;
            }
            count <<= 1;
            // 倒数第二层
            if (i == levels.size() - 2) {
                boolean firstNull = false;
                for (int j = 0; j < list.size(); j++) {
                    TreeNode node = list.get(j);
                    if (node.left == null && node.right != null) {
                        return false;
                    }
                    if (!firstNull) {
                        if (node.left == null || node.right == null) {
                            firstNull = true;
                        }
                    } else {
                        if (node.left != null || node.right != null) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (this.levels.size() == level) {
            this.levels.add(new ArrayList<>());
        }
        this.levels.get(level).add(node);

        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }

    public static void main(String[] args) {
        IsCompleteTree isCompleteTree = new IsCompleteTree();
        Assert.assertTrue(isCompleteTree.isCompleteTree(BinaryTreeUtil.deserialize("[1,2,3,5]")));
        Assert.assertFalse(isCompleteTree.isCompleteTree(BinaryTreeUtil.deserialize("[1,2,3,null,5]")));
        Assert.assertTrue(isCompleteTree.isCompleteTree(BinaryTreeUtil.deserialize("[1,2,3,4,5]")));
    }

}
