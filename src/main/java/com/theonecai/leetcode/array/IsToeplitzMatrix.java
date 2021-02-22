package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 * leetcode 766
 * @Author: theonecai
 * @Date: Create in 2021/2/22 22:12
 * @Description:
 */
public class IsToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (notEq(matrix, matrix[i][0], i + 1, 1)) {
                return false;
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (notEq(matrix, matrix[0][i], 1, i + 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean notEq(int[][] matrix, int value, int row, int col) {
        while (row < matrix.length && col < matrix[0].length) {
            if (value != matrix[row][col]) {
                return true;
            }
            row++;
            col++;
        }
        return false;
    }

    public static void main(String[] args) {
        IsToeplitzMatrix isToeplitzMatrix = new IsToeplitzMatrix();
        Assert.assertTrue(isToeplitzMatrix.isToeplitzMatrix(new int[][]{
                {1,2,3,4},
                {5,1,2,3},
                {9,5,1,2}
        }));
        Assert.assertTrue(isToeplitzMatrix.isToeplitzMatrix(new int[][]{
                {1}
        }));
        Assert.assertTrue(isToeplitzMatrix.isToeplitzMatrix(new int[][]{
                {1,1,1}
        }));
        Assert.assertTrue(isToeplitzMatrix.isToeplitzMatrix(new int[][]{
                {1},
                {1},
                {1}
        }));
        Assert.assertTrue(isToeplitzMatrix.isToeplitzMatrix(new int[][]{
                {1,2,3},
                {1,1,2},
                {1,1,1},
        }));
        Assert.assertFalse(isToeplitzMatrix.isToeplitzMatrix(new int[][]{
                {1,2,3},
                {1,2,2},
                {1,1,1},
        }));
    }
}
