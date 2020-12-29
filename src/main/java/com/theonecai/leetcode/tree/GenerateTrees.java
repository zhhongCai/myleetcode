package com.theonecai.leetcode.tree;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 95
 */
public class GenerateTrees {

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            List<TreeNode> leftList = buildSubTrees(1, i - 1);
            List<TreeNode> rightList = buildSubTrees(i + 1, n);
            list.addAll(build(i, leftList, rightList));
        }

        return list;
    }

    private List<TreeNode> build(int root, List<TreeNode> leftList, List<TreeNode> rightList) {
        List<TreeNode> list = new ArrayList<>();
        if (leftList != null && leftList.size() > 0) {
            for (TreeNode treeNode : leftList) {
                if (rightList == null || rightList.isEmpty()) {
                    TreeNode node = new TreeNode(root);
                    node.left = treeNode;
                    list.add(node);
                } else {
                    for (TreeNode value : rightList) {
                        TreeNode node = new TreeNode(root);
                        node.left = treeNode;
                        node.right = value;
                        list.add(node);
                    }
                }
            }
        } else {
            if (rightList == null || rightList.isEmpty()) {
                TreeNode node = new TreeNode(root);
                list.add(node);
            } else {
                for (TreeNode treeNode : rightList) {
                    TreeNode node = new TreeNode(root);
                    node.right = treeNode;
                    list.add(node);
                }
            }

        }
        return list;
    }

    private List<TreeNode> buildSubTrees(int start, int end) {
        if (start > end) {
            return Collections.emptyList();
        }
        List<TreeNode> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = buildSubTrees(start, i - 1);
            List<TreeNode> rightList = buildSubTrees(i + 1, end);
            list.addAll(build(i, leftList, rightList));
        }

        return list;
    }

    public static void main(String[] args) {
        GenerateTrees generateTrees = new GenerateTrees();
        List<TreeNode> list = generateTrees.generateTrees(3);
        Assert.assertEquals(5, list.size());
        RunUtil.runAndPrintCostTime(() -> {
            List<TreeNode> trees = generateTrees.generateTrees(8);
            System.out.println(trees.size());
        });
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
