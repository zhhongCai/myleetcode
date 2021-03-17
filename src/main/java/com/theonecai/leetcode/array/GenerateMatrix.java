package com.theonecai.leetcode.array;

import java.util.Arrays;

/**
 * leetcode 59
 * @Author: theonecai
 * @Date: Create in 2021/3/16 22:29
 * @Description:
 */
public class GenerateMatrix {

    private int current;

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        current = 1;
        generateMatrix(matrix, 0, 0, n, n);
        return matrix;
    }

    private void generateMatrix(int[][] matrix, int startRow, int startCol, int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            return;
        }
        for (int i = 0; i < cols; i++) {
            matrix[startRow][startCol + i] = current++;
        }
        if (rows > 1) {
            for (int i = 1; i < rows; i++) {
                matrix[startRow + i][startCol + cols - 1] = current++;
            }
            for (int i = 1; i < cols; i++) {
                matrix[startRow + rows - 1][startCol + cols - 1 - i] = current++;
            }
            if (cols > 1) {
                for (int i = 1; i < rows - 1; i++) {
                    matrix[startRow + rows -1 - i][startCol] = current++;
                }
            }
        }

        generateMatrix(matrix, startRow + 1, startCol + 1, rows - 2, cols - 2);
    }

    public static void main(String[] args) {
        GenerateMatrix generateMatrix = new GenerateMatrix();
        int[][] matrix = generateMatrix.generateMatrix(4);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
