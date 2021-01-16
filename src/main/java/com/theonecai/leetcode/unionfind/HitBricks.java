package com.theonecai.leetcode.unionfind;

import java.util.Arrays;

/**
 * 803
 */
public class HitBricks {

    /**
     *上右下左
     */
    private int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * directionsParent[0~3]表示上右下左四个方向的parent
     */
    private int[][] directionsParent;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] result = new int[hits.length];
        int rows = grid.length;
        int cols = grid[0].length;
        //directionsParent[0~3]表示上右下左四个方向的parent
        directionsParent = new int[4][rows * cols];
        int index;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                index = i * cols + j;
                for (int k = 0; k < directionsParent.length; k++) {
                    directionsParent[k][index] = index;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                index = i * cols + j;
                for (int k = 0; k < directions.length; k++) {
                    int[] direction = directions[k];
                    int row = i + direction[0];
                    int col = j + direction[1];
                    if (row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 1) {
                        union(directionsParent[k], index, row * cols + col);
                    }
                }
            }
        }

        for (int i = 0; i < hits.length; i++) {
            int[] hit = hits[i];
            if (grid[hit[0]][hit[1]] == 1) {
                result[i] = hitBrick(hit, grid);
            }
        }

        return result;
    }

    private int hitBrick(int[] hit, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        grid[hit[0]][hit[1]] = 0;
        resetParent(hit[0] * cols + hit[1]);
        for (int k = 0; k < directions.length; k++) {
            int[] direction = directions[k];
            int row = hit[0] + direction[0];
            int col = hit[1] + direction[1];
            if (checkRange(row, col, rows, cols) && grid[row][col] == 1 && row != 0) {
                if (!isBrickStableAfterHit(grid, row, col, hit)) {
//                    System.out.println("hit:" + row + "," + col);
                    count++;
                    count += hitBrick(new int[]{row, col}, grid);
                }
            }
        }

        return count;
    }

    private void resetParent(int index) {
        for (int i = 0; i < directionsParent.length; i++) {
            directionsParent[i][index] = Integer.MIN_VALUE;
        }
    }

    private boolean checkRange(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private boolean isBrickStableAfterHit(int[][] grid, int r, int l, int[] hit) {
        int hitIndex = hit[0] * grid[0].length + hit[1];
        for (int k = 0; k < directions.length; k++) {
            int[] direction = directions[k];
            int row = r + direction[0];
            int col = l + direction[1];
            if (row == hit[0] && col == hit[1]) {
                continue;
            }

            if (checkRange(row, col, grid.length, grid[0].length) && grid[row][col] == 1) {
                for (int i = 0; i < directionsParent.length; i++) {
                    int p = findParent(directionsParent[i], hitIndex);
                    int p2 = findParent(directionsParent[i], row * grid[0].length + col);
                    int p2p = -1;
                    if (p2 >= 0) {
                        p2p = findParent(directionsParent[0], p2);
                    }
                    if (p2 != p && p2p < grid[0].length && p2p >= 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int findParent(int[] parent, int x) {
        int i = x;
        while (i >= 0 && parent[i] != i) {
            if (parent[i] < 0) {
                return parent[i];
            }
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public void union(int[] parent, int x, int y) {
        int xParent = findParent(parent, x);
        int yParent = findParent(parent, y);
        if (xParent != yParent) {
            parent[xParent] = yParent;
        }
    }

    public static void main(String[] args) {
        HitBricks hitBricks = new HitBricks();
        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,1,1},
                {1,1,1}
        }, new int[][] {
                {1,2},{1,1},{1,0},{0,0},{0,1},{0,2}
        })));

        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,1},
                {1,1,1}
        }, new int[][] {
                {0,0},{0,2},{1,1}
        })));

        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,0,0},
                {1,1,1,0}
        }, new int[][] {
                {1,0}
        })));

        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,0,0,0,0},
                {1,0,1,1,1,1},
                {1,0,1,0,0,1},
                {1,1,1,1,1,1},
        }, new int[][] {
                {0,0}
        })));

        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,0,0},
                {1,1,0,0}
        }, new int[][] {
                {1,1},
                {1,0}
        })));


        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,0,0,0,1},
                {0,1,1,1,0,1},
                {0,1,1,1,0,1},
                {0,1,1,1,1,1},
        }, new int[][] {
                {3,5}
        })));

        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {0,0,0,0,0,1},
                {1,1,1,1,0,1},
                {1,1,1,1,0,1},
                {1,1,1,1,1,1},
        }, new int[][] {
                {3,5}
        })));
    }
}
