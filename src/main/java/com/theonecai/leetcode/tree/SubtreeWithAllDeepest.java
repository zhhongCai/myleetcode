package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * leetcode 865
 * @Author: theonecai
 * @Date: Create in 2021/1/31 21:17
 * @Description:
 */
public class SubtreeWithAllDeepest {

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }

        Map<TreeNode, TreeNode> fatherMap = new HashMap<>();
        List<List<TreeNode>> levelList = new ArrayList<>();

        dfs(root, null,0, fatherMap, levelList);

        List<TreeNode> maxLevelList = levelList.get(levelList.size() - 1);
        if (maxLevelList.size() == 1) {
            return maxLevelList.get(0);
        }
        while (true) {
            Set<TreeNode> parents = new HashSet<>();
            for (TreeNode node : maxLevelList) {
                parents.add(fatherMap.get(node));
            }
            if (parents.size() == 1) {
                return parents.iterator().next();
            }
            maxLevelList = new ArrayList<>(parents);
        }
    }

    private void dfs(TreeNode node, TreeNode father, int level, Map<TreeNode, TreeNode> fatherMap,
                     List<List<TreeNode>> levelList) {
        if (node == null) {
            return;
        }
        fatherMap.put(node, father);
        if (levelList.size() == level) {
            levelList.add(new ArrayList<>());
        }
        levelList.get(level).add(node);

        dfs(node.left, node, level + 1, fatherMap, levelList);
        dfs(node.right, node, level + 1, fatherMap, levelList);
    }

    public static void main(String[] args) {
        SubtreeWithAllDeepest subtree = new SubtreeWithAllDeepest();
        Assert.assertEquals("[2]",  BinaryTreeUtil.serialize(
                subtree.subtreeWithAllDeepest(BinaryTreeUtil.deserialize("[0,1,3,null,2]"))));
        Assert.assertEquals("[1]",  BinaryTreeUtil.serialize(
                subtree.subtreeWithAllDeepest(BinaryTreeUtil.deserialize("[1]"))));
        Assert.assertEquals("[2,7,4]",  BinaryTreeUtil.serialize(
                subtree.subtreeWithAllDeepest(BinaryTreeUtil.deserialize("[3,5,1,6,2,0,8,null,null,7,4]"))));
    }
}
