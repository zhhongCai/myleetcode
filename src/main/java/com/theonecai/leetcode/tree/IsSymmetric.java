package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.*;

/**
 * 101
 */
public class IsSymmetric {

    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 0;
        int i = 0;
        List<Integer> levelValues = new ArrayList<>();
        while (!queue.isEmpty()) {
            size = queue.size();
            i = 0;
            levelValues.clear();
            while (i < size) {
                TreeNode node = queue.poll();
                levelValues.add(node == null ? null : node.val);
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                i++;
            }
            if (!valueIsSymmetric(levelValues)) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, List<Integer>> levelMap;

    public boolean isSymmetric2(TreeNode root) {
        levelMap = new HashMap<>();

        visit(root, 0);

        for (List<Integer> values : levelMap.values()) {
            if (!valueIsSymmetric(values)) {
                return false;
            }
        }
        return true;
    }

    private boolean valueIsSymmetric(List<Integer> values) {
        if (values.size() < 2) {
            return true;
        }
        int left = 0;
        int right = values.size() - 1;
        Integer leftVal = null;
        Integer rightVal = null;
        while (left < right) {
            leftVal = values.get(left);
            rightVal = values.get(right);
            if ((leftVal == null && rightVal != null) || (leftVal != null && rightVal == null)) {
                return false;
            } else {
                if (leftVal != null && leftVal.compareTo(rightVal) != 0) {
                    return false;
                }
            }
            left++;
            right--;
        }
        return true;
    }

    private void visit(TreeNode node, int level) {
        List<Integer> list = levelMap.getOrDefault(level, new ArrayList<>());
        list.add(node == null ? null : node.val);
        levelMap.put(level, list);

        if (node != null) {
            visit(node.left, level + 1);
            visit(node.right, level + 1);
        }
    }

    public static void main(String[] args) {
        IsSymmetric isSymmetric = new IsSymmetric();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.right.right = new TreeNode(6);
        Assert.assertTrue(isSymmetric.isSymmetric(root));
    }
}
