package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/17 19:37
 * @Description:
 */
public class SwimmingPoolMinTime {

    public int swimInWater(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        visited[0][0] = true;
        int minHeight = grid[0][0];
        minHeap.add(new Node(0, 0, grid[0][0]));

        int row = grid.length;
        int col = grid[0].length;
        Node top;
        Node next;
        while (!minHeap.isEmpty()) {
            top = minHeap.poll();
            if (minHeight < top.getHeight()) {
                minHeight = top.getHeight();
            }
            if (top.getRowIndex() == row - 1 && top.getColIndex() == col - 1) {
                break;
            }

            // 下
            if (top.getRowIndex() < row - 1 && !visited[top.getRowIndex() + 1][top.getColIndex()]) {
                visited[top.getRowIndex() + 1][top.getColIndex()] = true;
                next = new Node(top.getRowIndex() + 1, top.getColIndex(), grid[top.getRowIndex() + 1][top.getColIndex()]);
                minHeap.add(next);
            }
            // 右
            if (top.getColIndex() < col - 1 && !visited[top.getRowIndex()][top.getColIndex() + 1]) {
                visited[top.getRowIndex()][top.getColIndex() + 1] = true;
                next = new Node(top.getRowIndex(), top.getColIndex() + 1, grid[top.getRowIndex()][top.getColIndex() + 1]);
                minHeap.add(next);
            }
            // 左
            if (top.getColIndex() > 0 && !visited[top.getRowIndex()][top.getColIndex() - 1]) {
                visited[top.getRowIndex()][top.getColIndex() - 1] = true;
                next = new Node(top.getRowIndex(), top.getColIndex() - 1, grid[top.getRowIndex()][top.getColIndex() - 1]);
                minHeap.add(next);
            }
            // 上
            if (top.getRowIndex() > 0 && !visited[top.getRowIndex() - 1][top.getColIndex()]) {
                visited[top.getRowIndex() - 1][top.getColIndex()] = true;
                next = new Node(top.getRowIndex() - 1, top.getColIndex(), grid[top.getRowIndex() - 1][top.getColIndex()]);
                minHeap.add(next);
            }
        }

        return minHeight;
    }

    static class Node implements Comparable<Node> {
        int rowIndex;
        int colIndex;
        int height;

        public Node(int rowIndex, int colIndex, int height) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
            this.height = height;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public int getColIndex() {
            return colIndex;
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
        SwimmingPoolMinTime swimmingPoolMinTime = new SwimmingPoolMinTime();
        int[][] nums = {
                {0, 1, 2, 3, 4, 5, 6},
                {0, 1, 2, 3, 4, 5, 6},
                {0, 1, 2, 3, 4, 5, 6},
                {9, 1, 2, 3, 4, 5, 6},
                {0, 1, 2, 3, 4, 5, 6},
                {0, 1, 2, 3, 4, 9, 6},
                {0, 1, 2, 7, 4, 2, 1},
        };
        long a = System.currentTimeMillis();
        Assert.assertEquals(4, swimmingPoolMinTime.swimInWater(nums));
    }
}
