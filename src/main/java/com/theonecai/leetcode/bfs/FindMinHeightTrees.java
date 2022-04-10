package com.theonecai.leetcode.bfs;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode 310
 */
public class FindMinHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>(2);
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            children[edge[0]].add(edge[1]);
            children[edge[1]].add(edge[0]);
        }

        int x = findLongestNode(0, children, parent);
        int y = findLongestNode(x, children, parent);
        parent[x] = -1;
        List<Integer> path = new ArrayList<>();
        path.add(y);
        while (parent[y] != -1) {
            y = parent[y];
            path.add(y);
        }
        if (path.size() % 2 == 0) {
            ans.add(path.get(path.size() / 2 - 1));
        }
        ans.add(path.get(path.size() / 2));

        return ans;
    }

    private int findLongestNode(int root, List<Integer>[] children, int[] parent) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] seen = new boolean[children.length];
        queue.add(root);
        seen[root] = true;
        int x = root;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Integer v : children[u]) {
                if (!seen[v]) {
                    seen[v] = true;
                    parent[v] = u;
                    queue.add(v);
                    x = v;
                }
            }
        }
        return x;
    }

    public static void main(String[] args) {
        FindMinHeightTrees findMinHeightTrees = new FindMinHeightTrees();
        Assert.assertArrayEquals(new int[]{1}, findMinHeightTrees.findMinHeightTrees(4, new int[][]{
                {1,0},{1,2},{1,3}
        }).stream().mapToInt(Integer::intValue).toArray());
        Assert.assertArrayEquals(new int[]{3,4}, findMinHeightTrees.findMinHeightTrees(6, new int[][]{
                {3,0},{3,1},{3,2},{3,4},{5,4}
        }).stream().mapToInt(Integer::intValue).toArray());
    }
}
