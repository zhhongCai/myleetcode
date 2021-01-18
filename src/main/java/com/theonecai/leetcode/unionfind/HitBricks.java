package com.theonecai.leetcode.unionfind;

import java.util.Arrays;

/**
 * 803
 */
public class HitBricks {

    /**
     *左上右下
     */
    private int[][] directions = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private int[] parent;
    private int[] count;
    private int rows;
    private int cols;
    private int size;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] result = new int[hits.length];
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.size = rows * cols;
        parent = new int[size + 1];
        count = new int[size + 1];
        int[][] matrix = new int[rows][cols];
        int index;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = grid[i][j];
            }
        }

        for (int[] hit : hits) {
            matrix[hit[0]][hit[1]] = 0;
        }
        Arrays.fill(count, 1);
        this.count[size] = 0;

        parent[size] = size;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                index = getIndex(i, j);
                parent[index] = index;
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0) {
                    union(parent, index, size);
                } else {
                    // 左上
                    for (int k = 0; k < 2; k++) {
                        int[] direction = directions[k];
                        int row = i + direction[0];
                        int col = j + direction[1];
                        if (checkRange(row, col) && matrix[row][col] == 1) {
                            union(parent, index, getIndex(row, col));
                        }
                    }
                }

            }
        }

        for (int i = hits.length - 1; i >= 0; i--) {
            result[i] = unHit(grid, matrix, hits[i]);
        }

        return result;
    }

    private int unHit(int[][] grid, int[][] matrix, int[] hit) {
        if (grid[hit[0]][hit[1]] == 0) {
            return 0;
        }
        if (hit[0] == 0) {
            union(parent, hit[1], size);
        }

        int count = 0;
        int original = getSize(size);
        int index = getIndex(hit[0], hit[1]);
        for (int[] direction : directions) {
            int row = hit[0] + direction[0];
            int col = hit[1] + direction[1];

            if (checkRange(row, col) && matrix[row][col] == 1) {
                int nextIndex = getIndex(row, col);
                union(parent, index, nextIndex);
            }
        }
        count += Math.max(0, getSize(size) - original - 1);
        matrix[hit[0]][hit[1]] = 1;

        return count;
    }

    private int getIndex(int row, int col) {
        return row * cols + col;
    }

    private int getSize(int x) {
        int p = findParent(parent, x);
        return count[p];
    }


    private boolean checkRange(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public int findParent(int[] parent, int x) {
        int i = x;
        while (i >= 0 && parent[i] != i) {
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
            count[yParent] += count[xParent];
        }
    }

    public static void main(String[] args) {
        HitBricks hitBricks = new HitBricks();
        //[[1,1,0,1,0},{1,1,0,1,1},{0,0,0,1,1},{0,0,0,1,0},{0,0,0,0,0},{0,0,0,0,0]]
        //[[5,1},{1,3]]

        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,1},
                {1,1,1}
        }, new int[][] {
                {0,0},{0,2},{1,1}
        })));

        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,1,0,1,0},
                {1,1,0,1,1},
                {0,0,0,1,1},
                {0,0,0,1,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
        }, new int[][] {
                {5,1},{1,3}
        })));

        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1},
                {1},
                {1},
                {1},
                {1},
        }, new int[][] {
                {3,0},{4,0},{1,0},{2,0},{0,0}
        })));

        System.out.println(Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,1,1},
                {1,1,1}
        }, new int[][] {
                {1,2},{1,1},{1,0},{0,0},{0,1},{0,2}
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
