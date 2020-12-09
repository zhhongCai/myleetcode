package com.theonecai.leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode 1632
 * @Author: theonecai
 * @Date: Create in 2020/11/9 22:15
 * @Description:
 */
public class MatrixRankTransform {


    public int[][] matrixRankTransform(int[][] matrix) {
        // 二维转为一维存索引
        Integer[] indexs = new Integer[matrix.length * matrix[0].length];
        // rowMax[i]表示i行当前最大秩所在列
        int[] rowMax = new int[matrix.length];
        // colMax[i]表示i列当前最大秩所在行
        int[] colMax = new int[matrix[0].length];
        // 并查集
        int[] parent = new int[indexs.length];
        // 秩
        int[] ranks = new int[indexs.length];
        Arrays.fill(rowMax, -1);
        Arrays.fill(colMax, -1);
        int idx = 0;
        final int cols = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < cols; j++) {
                indexs[idx] = idx;
                parent[idx] = idx;
                idx++;
            }
        }

        Arrays.sort(indexs, Comparator.comparingInt(a -> matrix[a / cols][a % cols]));

        for (int i = 0; i < indexs.length; i++) {
            int row = indexs[i] / cols;
            int col = indexs[i] % cols;

            int rank = 1;
            if (rowMax[row] != -1) {
                int index = rowMax[row] + row * cols;
                int rowRank = ranks[findParent(parent, index)];
                if (matrix[row][rowMax[row]] == matrix[row][col]) {
                    rank = Math.max(rank, rowRank);
                    union(parent, indexs[i], index);
                } else {
                    rank = Math.max(rank, rowRank + 1);
                }
            }

            if (colMax[col] != -1) {
                int index = colMax[col] * cols + col;
                int colRank = ranks[findParent(parent, index)];
                if (matrix[colMax[col]][col] == matrix[row][col]) {
                    rank = Math.max(rank,  colRank);
                    union(parent, indexs[i], index);
                } else {
                    rank = Math.max(rank, colRank + 1);
                }
            }

            ranks[findParent(parent, indexs[i])] = rank;
            rowMax[row] = col;
            colMax[col] = row;
        }

        int[][] result = new int[matrix.length][cols];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = ranks[findParent(parent, i * cols + j)];
            }
        }
        return result;
    }

    public int findParent(int[] parent, int x) {
        int i = x;
        while (parent[i] != i) {
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
        MatrixRankTransform rankTransform = new MatrixRankTransform();
        int[][] result = rankTransform.matrixRankTransform(new int[][]{
                {-23,20,-49,-30,-39,-28,-5,-14},
                {-19,4,-33,2,-47,28,43,-6},
                {-47,36,-49,6,17,-8,-21,-30},
                {-27,44,27,10,21,-8,3,14},
                {-19,12,-25,34,-27,-48,-37,14},
                {-47,40,23,46,-39,48,-41,18},
                {-27,-4,7,-10,9,36,43,2},
                {37,44,43,-38,29,-44,19,38},
        });
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
        System.out.println();
        result = rankTransform.matrixRankTransform(new int[][]{
                {-37,-26,-47,-40,-13},
                {22,-11,-44,47,-6},
                {-35,8,-45,34,-31},
                {-16,23,-6,-43,-20},
                {47,38,-27,-8,43},
        });
        System.out.println();
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
        result = rankTransform.matrixRankTransform(new int[][]{
                {-37,-50,-3,44},
                {-37,46,13,-32},
                {47,-42,-3,-40},
                {-17,-22,-39,24},
        });
        System.out.println();
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }

        result = rankTransform.matrixRankTransform(new int[][]{
                {1,2},
                {3,4},
        });
        System.out.println();
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
        result = rankTransform.matrixRankTransform(new int[][]{
                {7,7},
                {7,7},
        });
        System.out.println();
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
        result = rankTransform.matrixRankTransform(new int[][]{
                {20,-21,14},
                {-19,4,19},
                {22,-47,24},
                {-19,4,19},
        });
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
        System.out.println();
        result = rankTransform.matrixRankTransform(new int[][]{
                {7,3,6},
                {1,4,5},
                {9,8,2},
        });
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }
}
