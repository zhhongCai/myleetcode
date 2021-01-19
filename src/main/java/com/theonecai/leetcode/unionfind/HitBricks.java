package com.theonecai.leetcode.unionfind;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 803
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

        parent[size] = size;
        count[size] = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                index = getIndex(i, j);
                parent[index] = index;
                count[index] = 1;

                if (matrix[i][j] == 1) {
                    if (i == 0) {
                        union(parent, index, size);
                    }
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

//        printArray(matrix);

        int[] result = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            result[i] = unHit(grid, matrix, hits[i]);
        }

        return result;
    }

    private void printArray(int[][] matrix) {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
    }

    private int unHit(int[][] grid, int[][] matrix, int[] hit) {
        if (grid[hit[0]][hit[1]] == 0) {
            return 0;
        }

        int count = 0;
        int original = getSize(size);

        if (hit[0] == 0) {
            union(parent, hit[1], size);
        }

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

        Assert.assertEquals("[0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1]",
                Arrays.toString(hitBricks.hitBricks(new int[][]{
                {0,1,1,1,1,1},
                {1,1,1,1,1,1},
                {0,0,1,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        }, new int[][]{
                {1,3},{3,5},{0,3},{3,3},{1,1},{4,2},{1,0},{3,0},{4,5},{2,1},
                        {4,4},{4,0},{2,4},{2,5},{3,4},{0,5},{0,4},{3,2},{1,5},{4,1},{2,2},{0,2}
        })));

        Assert.assertEquals("[0, 4]", Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1, 1, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        }, new int[][]{
                {5, 1}, {1, 3}
        })));

        Assert.assertEquals("[0, 0, 0, 0, 0, 0]", Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,1,1},
                {1,1,1}
        }, new int[][] {
                {1,2},{1,1},{1,0},{0,0},{0,1},{0,2}
        })));

        Assert.assertEquals("[0, 3, 0]", Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,1},
                {1,1,1}
        }, new int[][] {
                {0,0},{0,2},{1,1}
        })));

        Assert.assertEquals("[2]", Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,0,0},
                {1,1,1,0}
        }, new int[][] {
                {1,0}
        })));

        Assert.assertEquals("[14]", Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,0,0,0,0},
                {1,0,1,1,1,1},
                {1,0,1,0,0,1},
                {1,1,1,1,1,1},
        }, new int[][] {
                {0,0}
        })));

        Assert.assertEquals("[0, 0]", Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,0,0},
                {1,1,0,0}
        }, new int[][] {
                {1,1},
                {1,0}
        })));


        Assert.assertEquals("[10]", Arrays.toString(hitBricks.hitBricks(new int[][]{
                {1,0,0,0,0,1},
                {0,1,1,1,0,1},
                {0,1,1,1,0,1},
                {0,1,1,1,1,1},
        }, new int[][] {
                {3,5}
        })));

        Assert.assertEquals("[13]", Arrays.toString(hitBricks.hitBricks(new int[][]{
                {0,0,0,0,0,1},
                {1,1,1,1,0,1},
                {1,1,1,1,0,1},
                {1,1,1,1,1,1},
        }, new int[][] {
                {3,5}
        })));

        int[][] grid = new int[100000][1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        grid[i][j] = 1;
                    }
                } else {
                    grid[i][j] = 1;
                }
            }
        }

        int result = (grid.length - 1) * grid[0].length;
        RunUtil.runAndPrintCostTime(() -> {
            Assert.assertEquals("[" +result+"]", Arrays.toString(hitBricks.hitBricks(grid, new int[][] {
                    {0,0}
            })));
        });
    }
}
