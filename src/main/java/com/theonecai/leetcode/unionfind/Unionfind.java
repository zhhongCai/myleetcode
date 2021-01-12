package com.theonecai.leetcode.unionfind;

/**
 * 并查集
 */
public class Unionfind {

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
