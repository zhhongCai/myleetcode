package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 685
 */
public class FindRedundantDirectedConnection {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        Unionfind unionfind = new Unionfind(edges.length + 1, true);
        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int u  = edge[0];
            int v  = edge[1];
            if (parent[v] != v) {
                conflict = i;
            } else {
                parent[v] = u;
                int up = unionfind.findParent(u);
                int vp = unionfind.findParent(v);
                if (up != vp) {
                    unionfind.union(up, vp);
                } else {
                    cycle = i;
                }
            }
        }

        if (conflict == -1) {
            return edges[cycle];
        } else {
            int[] conflictEdge = edges[conflict];
            if (cycle != -1) {
                return new int[] {parent[conflictEdge[1]], conflictEdge[1]};
            } else {
                return conflictEdge;
            }
        }
    }

    private static class Unionfind {

        private int[] parent;

        public Unionfind(int size, boolean defaultInit) {
            this.parent = new int[size];
            if (defaultInit) {
                initParent();
            }
        }

        public int[] getParent() {
            return this.parent;
        }

        public void initParent() {
            for (int i = 0; i < this.parent.length; i++) {
                this.parent[i] = i;
            }
        }

        public int findParent(int x) {
            int i = x;
            while (parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }

        public void union(int x, int y) {
            int xParent = findParent(x);
            int yParent = findParent(y);
            if (xParent != yParent) {
                parent[xParent] = yParent;
            }
        }

        /**
         * x,y是否连通
         * @param x
         * @param y
         * @return
         */
        public boolean isConnected(int x, int y) {
            return findParent(x) == findParent(y);
        }
    }

    public static void main(String[] args) {
        FindRedundantDirectedConnection findRedundantDirectedConnection = new FindRedundantDirectedConnection();
        Assert.assertEquals(Arrays.toString(new int[]{2,1}),
                Arrays.toString(findRedundantDirectedConnection.findRedundantDirectedConnection(new int[][]{
                        {2,1},{3,1},{4,2},{1,4}
                })));
        Assert.assertEquals(Arrays.toString(new int[]{1,4}),
                Arrays.toString(findRedundantDirectedConnection.findRedundantDirectedConnection(new int[][]{
                        {1,2},{2,3},{3,4},{1,4},{1,5}
                })));
        Assert.assertEquals(Arrays.toString(new int[]{2,3}),
                Arrays.toString(findRedundantDirectedConnection.findRedundantDirectedConnection(new int[][]{
                        {1,2},{1,3},{2,3}
                })));
        Assert.assertEquals(Arrays.toString(new int[]{3,4}),
                Arrays.toString(findRedundantDirectedConnection.findRedundantDirectedConnection(new int[][]{
                        {1,2},{2,3},{1,4},{3,4},{1,5}
                })));
    }
}
