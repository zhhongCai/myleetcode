package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Random;

/**
 * leetcode 1329
 * @Author: theonecai
 * @Date: Create in 2020/9/8 19:01
 * @Description:
 */
public class DiagonalSort {

    public int[][] diagonalSort(int[][] mat) {
        if (mat == null || mat.length < 2) {
            return mat;
        }

        for (int j = 0; j < mat[0].length - 1; j++) {
            diagonalSort(mat, 0, j);
        }
        for (int i = 1; i < mat.length - 1; i++) {
            diagonalSort(mat, i, 0);
        }

        return mat;
    }

    /**
     * 对角线桶排序
     * @param mat
     * @param startRow
     * @param startCol
     */
    private void diagonalSort(int[][] mat, int startRow, int startCol) {
        // bucket[i] = i出现个数
        int[] bucket = new int[101];

        int row = startRow;
        int col = startCol;
        while (row < mat.length && col < mat[0].length) {
            bucket[mat[row][col]] += 1;
            row++;
            col++;
        }
        row = startRow;
        col = startCol;
        int i = 0;
        while (row < mat.length && col < mat[0].length && i < bucket.length) {
            while (i < bucket.length && bucket[i] == 0) {
                i++;
            }
            if (i < bucket.length) {
                mat[row][col] = i;
                bucket[i] -= 1;
                row++;
                col++;
            }
        }
    }

    public static void main(String[] args) {
        DiagonalSort diagonalSort = new DiagonalSort();
        int[][] mat = new int[100][100];
        Random random = new Random(100);
        int n = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                n = random.nextInt(mat.length);
                mat[i][j] = n == 0 ? n + 1 : n;
            }
        }

        long a= System.currentTimeMillis();
        int[][] result = diagonalSort.diagonalSort(mat);
        System.out.println("cost: " + (System.currentTimeMillis() - a));

        for (int j = 0; j < mat[0].length - 1; j++) {
            diagonalSort.checkResult(result, 0, j);
        }
        for (int i = 1; i < mat.length - 1; i++) {
            diagonalSort.checkResult(result, i, 0);
        }
    }

    private void checkResult(int[][] result, int startRow, int startCol) {
        int row = startRow;
        int col = startCol;
        while (row < result.length && col < result[0].length) {
            if (row + 1 < result.length && col + 1 < result[0].length) {
                Assert.assertTrue(result[row][col] <= result[row + 1][col + 1]);
            }
            row++;
            col++;
        }
    }

}
