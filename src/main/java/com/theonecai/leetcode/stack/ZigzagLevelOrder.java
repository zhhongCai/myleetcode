package com.theonecai.leetcode.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 *
 * leetcode 103
 *
 * @Author: theoonecai
 * @Date: Create in 2020/7/29 20:22
 * @Description:
 */
public class ZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int level = 1;
        while (!stack.isEmpty()) {
            TreeNode[] levels = new TreeNode[stack.size()];
            int i = 0;
            while (!stack.isEmpty()) {
                levels[i++] = stack.pop();
            }

            for (i = 0; i < levels.length; i++) {
                TreeNode node = levels[i];
                if (level % 2 == 0) {
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                } else {
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                }
            }
            list.add(Arrays.stream(levels).map(n -> n.val).collect(Collectors.toList()));
            level++;
        }
        return list;
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ZigzagLevelOrder visit = new ZigzagLevelOrder();
        List<List<Integer>> list = visit.zigzagLevelOrder(root);
        list.forEach(System.out::println);
    }
}
