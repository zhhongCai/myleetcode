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
        if (edges == null || edges.length == 0) {
            return new int[0];
        }
        if (edges.length == 1) {
            return new int[]{0};
        }
        if (edges.length == 2) {
            return new int[]{1,1};
        }
        this.n = N;
        // Map<节点, 节点的下一个节点>
        Map<Integer, List<Integer>> tree = new HashMap<>(N);
        // 根节点
        int root = edges[0][0];
        int u;
        int v;
        for (int[] edge : edges) {
            if (edge[1] == root) {
                u = edge[1];
                v = edge[0];
            } else {
                u = edge[0];
                v = edge[1];
            }
            List<Integer> children = tree.getOrDefault(u, new ArrayList<>());
            children.add(v);
            tree.put(u, children);
        }
        // count[i] 表示以节点id为根的树的节点数量
        int[] count = new int[N];
        // dp[i] 表示节点i与其他所有节点距离之和
        int[] dp = new int[N];

        dfs(root, tree, count);

        dp[root] = countRoot(root, tree, count);

        countChildren(root, tree, count, dp);

        return dp;
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

    private int countRoot(int root, Map<Integer, List<Integer>> tree, int[] count) {
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
        Assert.assertEquals("[8,12,6,10,10,10]", Arrays.toString(
                sumOfDistancesInTree.sumOfDistancesInTree(6, new int[][]{
                        {0,1},{0,2},{2,3},{2,4},{2,5}
        })).replaceAll(" ", ""));
    }
}
