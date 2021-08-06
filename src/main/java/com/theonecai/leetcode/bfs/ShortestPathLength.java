package com.theonecai.leetcode.bfs;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 847
 * @Author: theonecai
 * @Date: Create in 8/6/21 20:38
 * @Description:
 */
public class ShortestPathLength {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int all = (1 << n) - 1;
        boolean[][] visited = new boolean[n][all + 1];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{i, 1 << i, 0});
            visited[i][1 << i] = true;
        }
        int res = 0;
        int u;
        int mask;
        int dist;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            u = cur[0];
            mask = cur[1];
            dist = cur[2];
            if (mask == all) {
                res = dist;
                break;
            }
            for (int v : graph[u]) {
                int maskV = mask | (1 << v);
                if (!visited[v][maskV]) {
                    queue.add(new int[]{v, maskV, dist + 1});
                    visited[v][maskV] = true;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ShortestPathLength pathLength = new ShortestPathLength();
        Assert.assertEquals(4, pathLength.shortestPathLength(new int[][]{
                {1,2,3},
                {0},
                {0},
                {0}
        }));

        Assert.assertEquals(4, pathLength.shortestPathLength(new int[][]{
                {1},{0,2,4},{1,3,4},{2},{1,2}
        }));
        Assert.assertEquals(0, pathLength.shortestPathLength(new int[][]{
                {}
        }));
    }
}
