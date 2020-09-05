package com.theonecai.leetcode.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 1305
 * @Author: theonecai
 * @Date: Create in 2020/9/5 10:52
 * @Description:
 */
public class TwoBinaryTreeGetAllElements {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        visit(root1, left);
        visit(root2, right);

        return merge(left, right);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> list = new ArrayList<>(left.size() + right.size());
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < left.size() || rightIndex < right.size()) {
            if (rightIndex >= right.size() ||
                    (leftIndex < left.size() && left.get(leftIndex) <= right.get(rightIndex))) {
                list.add(left.get(leftIndex++));
            } else {
                list.add(right.get(rightIndex++));
            }
        }
        return list;
    }

    private void visit(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            visit(node.left, list);
        }
        list.add(node.val);
        if (node.right != null) {
            visit(node.right, list);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TwoBinaryTreeGetAllElements getAllElements = new TwoBinaryTreeGetAllElements();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right= new TreeNode(3);
        List<Integer> list = getAllElements.getAllElements(null, null);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
