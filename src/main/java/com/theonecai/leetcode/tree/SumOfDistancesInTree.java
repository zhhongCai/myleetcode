package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 834
 */
public class SumOfDistancesInTree {

    private int n;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        if (N == 1) {
            return new int[] {0};
        }
        if (edges == null || edges.length == 0) {
            return new int[0];
        }

        this.n = N;
        // 根节点
        int root = findRoot(edges);
        int u;
        int v;
        // Map<节点, 节点的下一个节点>
        Map<Integer, List<Integer>> tree = buildTree(root, edges);

        // count[i] 表示以节点id为根的树的节点数量
        int[] count = new int[N];
        // dp[i] 表示节点i与其他所有节点距离之和
        int[] dp = new int[N];

        dfs(root, tree, count);

        dp[root] = countRoot(root, tree);

        countChildren(root, tree, count, dp);

        return dp;
    }

    private Map<Integer, List<Integer>> buildTree(int root, int[][] edges) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        int u;
        int v;
        Map<Integer, List<Integer>> nodeMap = new HashMap<>();
        for (int[] edge : edges) {
            u = edge[0];
            v = edge[1];
            List<Integer> children = nodeMap.getOrDefault(u, new ArrayList<>());
            children.add(v);
            nodeMap.put(u, children);

            List<Integer> children2 = nodeMap.getOrDefault(v, new ArrayList<>());
            children2.add(u);
            nodeMap.put(v, children2);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        boolean[] visited = new boolean[this.n];
        visited[root] = true;
        while (!queue.isEmpty()) {
            int i = queue.size();
            while (i > 0) {
                int node = queue.poll();

                List<Integer> children = nodeMap.get(node);
                if (children != null) {
                    List<Integer> children2 = tree.getOrDefault(node, new ArrayList<>());
                    for (Integer child : children) {
                        if (!visited[child]) {
                            queue.add(child);
                            children2.add(child);
                            visited[child] = true;
                        }
                    }
                    tree.put(node, children2);
                }
                i--;
            }
        }

        return tree;
    }

    private int findRoot(int[][] edges) {
        boolean[] exists = new boolean[this.n];
        for (int[] edge : edges) {
            exists[Math.max(edge[0], edge[1])] = true;
        }
        for (int i = 0; i < this.n; i++) {
            if (!exists[i]) {
                return i;
            }
        }
        return 0;
    }

    private int dfs(int node, Map<Integer, List<Integer>> tree, int[] count) {
        List<Integer> children = tree.get(node);
        if (children == null || children.size() == 0) {
            count[node] = 1;
            return 1;
        }
        int c = 1;
        for (Integer child : children) {
            c += dfs(child, tree, count);
        }
        count[node] = c;
        return count[node];
    }

    private void countChildren(int root, Map<Integer, List<Integer>> tree, int[] count, int[] dp) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int i = queue.size();
            while (i > 0) {
                int parent = queue.poll();
                List<Integer> children = tree.get(parent);
                if (children != null) {
                    for (Integer child : children) {
                        // dp[child] = dp[parent] - child的子节点数量 + child兄弟节点及其子节点数量  + parent树以外的其他节点数量
                        dp[child] = dp[parent] - (count[child] - 1) + (count[parent] - count[child] - 1) + (this.n - count[parent]);
                        queue.offer(child);
                    }
                }
                i--;
            }
        }
    }

    private int countRoot(int root, Map<Integer, List<Integer>> tree) {
        int level = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            int i = queue.size();
            while (i > 0) {
                int node = queue.poll();
                List<Integer> children = tree.get(node);
                if (children != null) {
                    sum += level * children.size();
                    queue.addAll(children);
                }
                i--;
            }
            level++;
        }
        return sum;
    }

    public static void main(String[] args) {
        SumOfDistancesInTree sumOfDistancesInTree = new SumOfDistancesInTree();
        Assert.assertEquals("[3,3,2]", Arrays.toString(
                sumOfDistancesInTree.sumOfDistancesInTree(3, new int[][]{
                        {2,1},{0,2}
        })).replaceAll(" ", ""));
        Assert.assertEquals("[2,3,3]", Arrays.toString(
                sumOfDistancesInTree.sumOfDistancesInTree(3, new int[][]{
                        {2,0},{1,0}
        })).replaceAll(" ", ""));
        Assert.assertEquals("[8,12,6,10,10,10]", Arrays.toString(
                sumOfDistancesInTree.sumOfDistancesInTree(6, new int[][]{
                        {0,1},{0,2},{2,3},{2,4},{2,5}
                })).replaceAll(" ", ""));
        Assert.assertEquals("[2,3,3]", Arrays.toString(
                sumOfDistancesInTree.sumOfDistancesInTree(3, new int[][]{
                        {0,1},{0,2}
        })).replaceAll(" ", ""));
        Assert.assertEquals("[15,11,9,9,11,15]", Arrays.toString(
                sumOfDistancesInTree.sumOfDistancesInTree(6, new int[][]{
                        {0,1},{1,2},{2,3},{3,4},{4,5}
        })).replaceAll(" ", ""));
        Assert.assertEquals("[0]", Arrays.toString(
                sumOfDistancesInTree.sumOfDistancesInTree(1, new int[][]{
                        {}
        })).replaceAll(" ", ""));
    }
}
