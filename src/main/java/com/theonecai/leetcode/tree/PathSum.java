package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * leetcode 113
 * @Author: theonecai
 * @Date: Create in 2020/10/31 20:04
 * @Description:
 */
public class PathSum {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
//        dfs2(root, result, sum);
        dfs(root, result, new ArrayList<>(), sum);

        return result;
    }

    private void dfs(TreeNode node, List<List<Integer>> result, List<Integer> path, int sum) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        if (node.left == null && node.right == null && node.val == sum) {
            List<Integer> list = new ArrayList<>(path.size());
            list.addAll(path);
            result.add(list);
        }

        if (node.left != null) {
            dfs(node.left, result, path, sum - node.val);
        }
        if (node.right != null) {
            dfs(node.right, result, path, sum - node.val);
        }
        path.remove(path.size() - 1);
    }


    private void dfs2(TreeNode root, List<List<Integer>> result, int sum) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> path = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            while(!path.isEmpty() && path.peek().left != node && path.peek().right != node) {
                sum += path.pop().val;
            }
            if (node != null) {
                path.push(node);
                if (node.val == sum && node.left == null && node.right == null) {
                    result.add(path.stream().mapToInt(t -> t.val).boxed().collect(Collectors.toList()));
                }
                sum -= node.val;
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }

    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        PathSum pathSum = new PathSum();

        TreeNode root = new TreeNode(-2);
        root.right = new TreeNode(-3);
        List<List<Integer>> list = pathSum.pathSum(root, -5);
        for (List<Integer> nums : list) {
            System.out.println(nums);
        }

        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        list = pathSum.pathSum(root, 22);
        for (List<Integer> nums : list) {
            System.out.println(nums);
        }
    }
}
