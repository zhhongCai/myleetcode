package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 199
 */
public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        // Map<level, 最右节点>
        Map<Integer, Integer> levelMap = new HashMap<>();

        dfs(root, 0, levelMap);

        return new ArrayList<>(levelMap.values());
    }

    private void dfs(TreeNode node, int level, Map<Integer, Integer> levelMap) {
        if (node == null) {
            return;
        }
        levelMap.put(level, node.val);

        dfs(node.left, level + 1, levelMap);
        dfs(node.right, level + 1, levelMap);
    }

    public static void main(String[] args) {
        RightSideView view = new RightSideView();
        TreeNode root = TreeNode.buildTree();
        System.out.println(view.rightSideView(root));
        System.out.println(view.rightSideView(null));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        System.out.println(view.rightSideView(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        System.out.println(view.rightSideView(root));
    }
}
