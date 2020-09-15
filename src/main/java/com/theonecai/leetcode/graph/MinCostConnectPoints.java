package com.theonecai.leetcode.graph;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * leetcode 5513
 * @Author: theoonecai
 * @Date: Create in 2020/9/13 15:37
 * @Description:
 */
public class MinCostConnectPoints {

    public int minCostConnectPoints(int[][] points) {
        List<Edge> edges = new ArrayList<>(points.length * (points.length + 1 ) / 2);
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                edges.add(new Edge(i, j,
                        Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }

        Collections.sort(edges);

        return kruskal(edges, points.length);
    }

    public int minCostConnectPoints2(int[][] points) {
        return prime(points, points.length);
    }

    /**
     * prime: 最小生成树
     *
     * 核心思想：
     * BFS+最小堆
     *
     * 1.随机取一个点，这个点直接可达的点的边加入堆
     * 2.取最小边e，边e对应到达到点v,v还没访问过时,取v直接可达的点的边(排除v到已访问的点的边)入堆
     * 3.重复步骤2，直到边数=点数-1
     * @param points
     * @param n
     * @return
     */
    private int prime(int[][] points, int n) {
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        visited[0] = true;
        for (int i = 1; i < points.length; i++) {
            minHeap.add(new Edge(0, i,
                    Math.abs(points[i][0] - points[0][0]) + Math.abs(points[i][1] - points[0][1])));
        }
        int edgeCount = 0;
        int dist = 0;
        while (edgeCount < n - 1) {
            Edge edge = minHeap.poll();
            if (visited[edge.v]) {
                continue;
            }
            dist += edge.dist;
            edgeCount++;
            visited[edge.v] = true;
            for (int i = 0; i < points.length; i++) {
                if (visited[i]) {
                    continue;
                }
                minHeap.add(new Edge(edge.v, i, Math.abs(points[i][0] - points[edge.v][0])
                        + Math.abs(points[i][1] - points[edge.v][1])));
            }
        }
//        System.out.println();
        return dist;
    }

    /**
     * kruskal: 最小生成树
     *
     * 核心思想：
     * 每次取最小边e(贪心)
     * 对取的边e判断是否构成环(并查集)
     *
     * 步骤：
     * 1.对所有边按距离排序
     * 2.循环取出最小的边e
     * 3.判断加入边e时是否构成环，不构成才可加入
     *   3.1 使用并查集判断边e的两个点u,v是否有相同的根,相同根则有环
     * 4.直到边数=点数-1
     * @param edgeList
     * @param n
     * @return
     */
    private int kruskal(List<Edge> edgeList, int n) {
        // root[i] = v 表示点i到v
        int[] root = new int[n];
        Arrays.fill(root, -1);

        int dist = 0;
        int edgeCount = 0;
        for (Edge edge : edgeList) {
            int uRoot = find(root, edge.u);
            int vRoot = find(root, edge.v);
            // 没有环
            if (uRoot != vRoot) {
                root[uRoot] = vRoot;
                dist += edge.dist;
                edgeCount++;
            }
            if (edgeCount >= n - 1) {
                break;
            }
        }
        return dist;
    }

    private int find(int[] root, int v) {
        int i = v;
        while (root[i] != -1) {
            i = root[i];
        }
        if (i != v) {
            root[v] = i;
        }
        return i;
    }


    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int dist;

        public Edge(int u, int v, int dist) {
            this.u = u;
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) {
        MinCostConnectPoints minCostConnectPoints = new MinCostConnectPoints();
        int[][] points = {
                {0,0},
                {2,2},
                {3,10},
                {5,2},
                {7,0},
        };
        Assert.assertEquals(20, minCostConnectPoints.minCostConnectPoints(points));
        Assert.assertEquals(20, minCostConnectPoints.minCostConnectPoints2(points));
        Assert.assertEquals(102, minCostConnectPoints.minCostConnectPoints(new int[][]{
                {-14,-14},{-18,5},{18,-10},{18,18},{10,-2}
        }));
        Assert.assertEquals(102, minCostConnectPoints.minCostConnectPoints2(new int[][]{
                {-14,-14},{-18,5},{18,-10},{18,18},{10,-2}
        }));
    }
}

