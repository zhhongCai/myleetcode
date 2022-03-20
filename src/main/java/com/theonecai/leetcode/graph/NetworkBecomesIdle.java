package com.theonecai.leetcode.graph;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode 2039
 */
public class NetworkBecomesIdle {

    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            children[edge[0]].add(edge[1]);
            children[edge[1]].add(edge[0]);
        }

        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        bfs(children, dist);

        int ans = 0;
        for (int i = 1; i < n; i++) {
            int t = 2 * dist[i];
            if (patience[i] < t) {
                t += (t - patience[i]) / patience[i] * patience[i];
            }
            ans = Math.max(ans, t);
        }

        return ans + 1;
    }

    private void bfs(List<Integer>[] children, int[] dist) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        dist[0] = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            List<Integer> list = children[u];
            for (Integer v : list) {
                if (dist[v] != -1) {
                    continue;
                }
                queue.add(v);
                dist[v] = dist[u] + 1;
            }
        }
    }

    public static void main(String[] args) {
        NetworkBecomesIdle idle = new NetworkBecomesIdle();
        Assert.assertEquals(8, idle.networkBecomesIdle(new int[][]{
                {0,1},
                {1,2},
        }, new int[]{0,2,1}));
        Assert.assertEquals(3, idle.networkBecomesIdle(new int[][]{
                {0,1},
                {0,2},
                {1,2},
        }, new int[]{0,10,10}));
    }
}
