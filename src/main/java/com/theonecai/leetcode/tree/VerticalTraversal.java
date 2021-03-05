package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * leetcode 987
 * @Author: theonecai
 * @Date: Create in 2021/3/5 21:32
 * @Description:
 */
public class VerticalTraversal {
    private static class Node implements Comparable<Node> {
        private int val;
        private int row;
        private int col;

        public Node(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }


        @Override
        public int compareTo(Node o) {
            int result = this.row - o.row;
            if (result == 0) {
                return this.val - o.val;
            }
            return result;
        }
    }

    private Map<Integer, List<Node>> map;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        map = new TreeMap<>();

        dfs(root, 0, 0);

        List<List<Integer>> result = new ArrayList<>();
        for (Map.Entry<Integer, List<Node>> entry : map.entrySet()) {
            List<Node> nodes = entry.getValue();
            Collections.sort(nodes);
            List<Integer> list = new ArrayList<>(entry.getValue().size());
            for (Node node : entry.getValue()) {
                list.add(node.val);
            }
            result.add(list);
        }

        return result;
    }

    private void dfs(TreeNode node, int row, int col) {
        if (node == null) {
            return;
        }
        List<Node> list = map.getOrDefault(col, new ArrayList<>());
        list.add(new Node(node.val, row, col));
        map.put(col, list);

        dfs(node.left, row + 1, col - 1);
        dfs(node.right, row + 1, col + 1);
    }

    public static void main(String[] args) {
        VerticalTraversal verticalTraversal = new VerticalTraversal();
        List<List<Integer>> list = verticalTraversal.verticalTraversal(
                BinaryTreeUtil.deserialize("[3,9,20,null,null,15,7]"));
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
        System.out.println();

        list = verticalTraversal.verticalTraversal(
                BinaryTreeUtil.deserialize("[1,2,3,4,5,6,7]"));
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
        System.out.println();

        list = verticalTraversal.verticalTraversal(
                BinaryTreeUtil.deserialize("[1,2,3,4,6,5,7]"));
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
        System.out.println();

        list = verticalTraversal.verticalTraversal(
                BinaryTreeUtil.deserialize("[3,1,4,0,2,2]"));
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
    }
}
