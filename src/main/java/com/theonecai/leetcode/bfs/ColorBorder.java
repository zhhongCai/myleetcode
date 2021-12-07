package com.theonecai.leetcode.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode 1034
 */
public class ColorBorder {

    private int m;
    private int n;
    private boolean[][] seen;
    private int[][] direction = new int[][]{
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    public int[][] colorBorder2(int[][] grid, int row, int col, int color) {
        m = grid.length;
        n = grid[0].length;
        seen = new boolean[m][n];
//        return bfs(grid, row, col, color);

        List<int[]> border = new ArrayList<>();
        dfs(grid, row, col, grid[row][col], color, border);
        for (int[] b : border) {
            grid[b[0]][b[1]] = color;
        }

        return grid;
    }

    private void dfs(int[][] grid, int row, int col, int startColor, int color, List<int[]> border) {
        if (!inRange(row, col) || seen[row][col] || grid[row][col] != startColor) {
            return;
        }
        seen[row][col] = true;
        int count = 0;
        for (int[] d : direction) {
            int x = row + d[0];
            int y = col + d[1];
            if (inRange(x, y)) {
                if (grid[x][y] == startColor) {
                    count++;
                }
                dfs(grid, x, y, startColor, color, border);
            }
        }
        if (count < 4 || isBorder(row, col)) {
            border.add(new int[]{row, col});
        }
    }

    public int[][] bfs(int[][] grid, int row, int col, int color) {

        List<int[]> border = new ArrayList<>();

        seen = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        seen[row][col] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int cnn = 0;
                for (int[] d : direction) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    if (inRange(x, y)) {
                        if (!seen[x][y] && grid[x][y] == grid[row][col]) {
                            queue.add(new int[]{x, y});
                        }
                        if (grid[x][y] == grid[row][col]) {
                            cnn++;
                        }
                        seen[x][y] = true;
                    }

                }
                if (cnn < 4 || isBorder(cur[0], cur[1])) {
                    border.add(cur);
                }
            }
        }
        for (int[] x : border) {
            grid[x[0]][x[1]] = color;
        }
        return grid;
    }

    private boolean inRange(int x, int y) {
        return 0 <= x && x <m && 0 <= y && y < n;
    }

    private boolean isBorder(int x, int y) {
        return x == 0 || x == m - 1 || y == 0 || y == n - 1;
    }

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        m = grid.length;
        n = grid[0].length;
        int[] parent = new int[m * n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] d : direction) {
                    int r = i + d[0];
                    int c = j + d[1];
                    if (inRange(r, c) && grid[i][j] == grid[r][c]) {
                        union(parent, getIndex(i, j), getIndex(r, c));
                    }
                }
            }
        }

        int start = getIndex(row, col);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (findParent(parent, getIndex(i, j)) != findParent(parent, start)) {
                    continue;
                }
                if (isBorder(i, j)) {
                    grid[i][j] = color;
                    continue;
                }
                int count = 0;
                for (int[] d : direction) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (inRange(x, y) && findParent(parent, getIndex(x, y)) == findParent(parent, start)) {
                        count++;
                    }
                }
                if (count < 4) {
                    grid[i][j] = color;
                }
            }
        }

        return grid;
    }

    private int getIndex(int r, int c) {
        return r * n + c;
    }

    private int findParent(int[] parent, int x) {
        int i = x;
        while (parent[i] != i) {
            i = parent[i];
        }
        parent[x] = i;
        return i;
    }

    private void union(int[] parent, int x, int y) {
        int xParent = findParent(parent, x);
        int yParent = findParent(parent, y);
        if (xParent != yParent) {
            parent[xParent] = parent[yParent];
        }
    }

    public static void main(String[] args) {
        ColorBorder colorBorder = new ColorBorder();
        int[][] grid = colorBorder.colorBorder(new int[][]{
                {1,1,1,1,1},
                {1,2,2,1,1},
                {1,2,2,2,1},
                {1,1,2,1,1},
        }, 1, 1, 3);
        for (int[] ints : grid) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
