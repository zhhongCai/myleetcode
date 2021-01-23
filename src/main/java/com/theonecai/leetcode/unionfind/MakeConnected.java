package com.theonecai.leetcode.unionfind;

import org.junit.Assert;

/**
 * leetcode 1319
 * @Author: theonecai
 * @Date: Create in 2021/1/23 19:42
 * @Description:
 */
public class MakeConnected {
    public int makeConnected(int n, int[][] connections) {
        Unionfind unionfind = new Unionfind(n, true);
        if (connections.length < n - 1) {
            return -1;
        }
        int count = n - 1;
        for (int[] connection : connections) {
            int xp = unionfind.findParent(connection[0]);
            int yp = unionfind.findParent(connection[1]);
            if (xp != yp) {
                count--;
                unionfind.union(xp, yp);
            }
        }

        return count;
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
        MakeConnected makeConnected = new MakeConnected();
        Assert.assertEquals(1, makeConnected.makeConnected(4, new int[][]{{0,1},{0,2},{1,2}}));
        Assert.assertEquals(2, makeConnected.makeConnected(6, new int[][]{{0,1},{0,2},{0,3},{1,2},{1,3}}));
        Assert.assertEquals(-1, makeConnected.makeConnected(6, new int[][]{{0,1},{0,2},{0,3},{1,2}}));
        Assert.assertEquals(0, makeConnected.makeConnected(5, new int[][]{{0,1},{0,2},{3,4},{2,3}}));
    }
}
