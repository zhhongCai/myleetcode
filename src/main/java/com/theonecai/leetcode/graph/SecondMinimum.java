package com.theonecai.leetcode.graph;

import org.junit.Assert;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * leetcode 2045
 */
public class SecondMinimum {

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> vMap = new HashMap(n);
        for (int[] edge : edges) {
            vMap.putIfAbsent(edge[0], new ArrayList<>());
            vMap.putIfAbsent(edge[1], new ArrayList<>());
            vMap.get(edge[0]).add(edge[1]);
            vMap.get(edge[1]).add(edge[0]);
        }

//        return bfs(n, vMap, time, change);
        return dijskra(n, vMap, time, change);
    }

    private int bfs(int n, Map<Integer, List<Integer>> vMap, int time, int change) {
        int[][] dist = new int[n + 1][2];
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        dist[1][0] = 0;
        while (queue.size() > 0) {
            int[] edge = queue.poll();
            int u = edge[0];
            int d = edge[1];
            for (int v : vMap.get(u)) {
                if (dist[v][0] > d + 1) {
                    dist[v][1] = dist[v][0];
                    dist[v][0] = d + 1;
                    queue.add(new int[]{v, dist[v][0]});
                } else if (dist[v][0] < d + 1 && d + 1 < dist[v][1]) {
                    dist[v][1] = d + 1;
                    queue.add(new int[]{v, d + 1});
                }
            }
        }

        int cost = 0;
        int c = 2 * change;
        for (int i = 0; i < dist[n][1]; i++) {
            if (cost % c >= change) {
                cost += c - (cost % c);
            }
            cost += time;
        }
        return cost;
    }

    private int dijskra(int n, Map<Integer, List<Integer>> vMap, int time, int change) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[] {1, 0});
        int cost = 0;
        int c = 2 * change;
        int[][] dist = new int[n + 1][2];
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        while (queue.size() > 0) {
            int[] edge = queue.poll();
            int t = edge[1];
            int md = t % c;
            t += (md < change ? 0 : c - md);
            for (int v : vMap.get(edge[0])) {
                if (dist[v][0] > t + time) {
                    dist[v][1] = dist[v][0];
                    dist[v][0] = t + time;
                    queue.add(new int[] {v, dist[v][0]});
                } else if (dist[v][0] < t + time && t + time < dist[v][1]) {
                    dist[v][1] = t + time;
                    queue.add(new int[] {v, dist[v][1]});
                }
            }

        }
        return dist[n][1];
    }

    public static void main(String[] args) {
        SecondMinimum secondMinimum = new SecondMinimum();
        Assert.assertEquals(13, secondMinimum.secondMinimum(5, new int[][]{
                {1,2},{1,3},{1,4},{3,4},{4,5}}, 3, 5));
        Assert.assertEquals(11, secondMinimum.secondMinimum(2, new int[][]{
                {1,2}}, 3, 2));
    }
}
