package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 257
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();

        dfs(root, new ArrayList<>(), result);

        return result;
    }

    private void dfs(TreeNode node, ArrayList<Integer> list, List<String> result) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        int size = list.size();
        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(list.get(i));
                if (i < size - 1) {
                    sb.append("->");
                }
            }
            result.add(sb.toString());
            list.remove(size - 1);
            return;
        }
        dfs(node.left, list, result);
        dfs(node.right, list, result);
        list.remove(size - 1);
    }

    public static void main(String[] args) {
        BinaryTreePaths paths = new BinaryTreePaths();
        List<String> list = paths.binaryTreePaths(TreeNode.buildTree());
        for (String s : list) {
            System.out.println(s);
        }
        list = paths.binaryTreePaths(new TreeNode(1));
        for (String s : list) {
            System.out.println(s);
        }
        list = paths.binaryTreePaths(null);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
