package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * leetcode 407
 *
 * 最小堆 + dfs
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/13 22:04
 * @Description:
 */
public class TrapRainWater {
    /**
     * 已访问位置
     */
    private int visitedCount;
    /**
     * 位置总数
     */
    private int total;
    /**
     * 是否访问过
     */
    boolean[][] visited;
    /**
     * 最小堆
     */
    private PriorityQueue<Node> minHeap;


    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length < 3 || heightMap[0].length < 3) {
            return 0;
        }

        int row = heightMap.length;
        int col = heightMap[0].length;
        visitedCount = 0;
        total = row * col;
        // 是否访问过
        visited = new boolean[row][col];
        minHeap = new PriorityQueue<>((row + col) *3);

        borderAddToHeap(heightMap);

        int water = 0;
        while (!minHeap.isEmpty() && visitedCount < total) {
            water += processNode(heightMap, minHeap.poll());
        }

        return water;
    }

    /**
     * 处理堆顶元素，对其四周取未访问的位置：若高度>=堆顶高度 入堆，否则计算水量；标记为已访问
     * @param heightMap
     * @param node
     * @return
     */
    private int processNode(int[][] heightMap, Node node) {
        if (visitedCount >= total) {
            return 0;
        }
        int h = 0;
        // 上方
        if (node.row > 0 && !visited[node.row - 1][node.col]) {
            h += processNext(node.row - 1, node.col, heightMap, node);
        }
        // 右边
        if (node.col < heightMap[0].length - 1 && !visited[node.row][node.col + 1]) {
            h += processNext(node.row, node.col + 1, heightMap, node);
        }
        // 下方
        if (node.row < heightMap.length - 1 && !visited[node.row + 1][node.col]) {
            h += processNext(node.row + 1, node.col, heightMap, node);
        }
        // 左边
        if (node.col > 0 && !visited[node.row][node.col - 1]) {
            h += processNext(node.row, node.col - 1, heightMap, node);
        }

        return h;
    }

    private int processNext(int nextRow, int nextCol, int[][] heightMap, Node node) {
        // 标记为已访问
        visited[nextRow][nextCol] = true;
        visitedCount += 1;
        int nextHeight = heightMap[nextRow][nextCol];

        if (nextHeight >= node.height) {
            minHeap.add(new Node(nextRow, nextCol, nextHeight));
            return 0;
        }

        int h = node.height - nextHeight;

        // dfs计算以node为边界的其他位置的水量
        h += processNode(heightMap, new Node(nextRow, nextCol, node.height));
        return h;
    }


    /**
     * 最外围边界入堆(扣除左上角，左下角，右上角，右下角)
     * @param heightMap
     */
    private void borderAddToHeap(int[][] heightMap) {
        int row = heightMap.length;
        int col = heightMap[0].length;
        for (int i = 1; i < row - 1; i++) {
            minHeap.add(new Node(i, 0, heightMap[i][0]));
            minHeap.add(new Node(i, col - 1, heightMap[i][col - 1]));
            visited[i][0] = true;
            visited[i][col - 1] = true;
        }
        for (int j = 1; j < col - 1; j++) {
            minHeap.add(new Node(0, j, heightMap[0][j]));
            minHeap.add(new Node(row - 1, j, heightMap[row - 1][j]));
            visited[0][j] = true;
            visited[row - 1][j] = true;
        }
        visited[0][0] = true;
        visited[0][col - 1] = true;
        visited[row - 1][0] = true;
        visited[row - 1][col - 1] = true;

        visitedCount += col * 2 + (row - 2) * 2;
    }

    static class Node implements Comparable<Node> {
        int row;
        int col;
        int height;

        public Node(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public int compareTo(Node o) {
            return this.height - o.height;
        }
    }

    public static void main(String[] args) {
        TrapRainWater trapRainWater = new TrapRainWater();
        int[][] heightMap = {
                {1,4,4,4,5,2},
                {4,2,3,2,2,4},
                {4,2,6,2,2,4},
                {4,2,4,2,2,4},
                {2,4,4,4,4,1}
        };
        Assert.assertEquals(19, trapRainWater.trapRainWater(heightMap));
    }
}
