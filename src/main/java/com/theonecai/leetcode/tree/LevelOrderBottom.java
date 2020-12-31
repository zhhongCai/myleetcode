package com.theonecai.leetcode.tree;

import java.util.*;

/**
 * 107
 */
public class LevelOrderBottom {
    List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Map<Integer, List<Integer>> levelMap = new TreeMap<>(Comparator.reverseOrder());
        Queue<LevelNode> queue = new LinkedList<>();
        queue.offer(new LevelNode(0, root));
        while (!queue.isEmpty()) {
            LevelNode levelNode = queue.poll();
            levelMap.computeIfAbsent(levelNode.level, ArrayList::new).add(levelNode.node.val);

            if (levelNode.node.left != null) {
                queue.offer(new LevelNode(levelNode.level + 1, levelNode.node.left));
            }
            if (levelNode.node.right != null) {
                queue.offer(new LevelNode(levelNode.level + 1, levelNode.node.right));
            }
        }

        return new ArrayList<>(levelMap.values());
    }

    private static class LevelNode {
        int level;
        TreeNode node;

        public LevelNode(int level, TreeNode node) {
            this.level = level;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        LevelOrderBottom levelOrderBottom = new LevelOrderBottom();
        List<List<Integer>> list = levelOrderBottom.levelOrderBottom(TreeNode.buildTree());
        for (List<Integer> nums : list) {
            System.out.println(nums);
        }
    }
    
}
