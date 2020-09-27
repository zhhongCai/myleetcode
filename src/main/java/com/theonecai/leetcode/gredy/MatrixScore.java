package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 861
 * @Author: theonecai
 * @Date: Create in 2020/9/27 20:04
 * @Description:
 */
public class MatrixScore {

    public int matrixScore(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i][0] == 0) {
                reverseRow(A, i);
            }
        }
        for (int col = 0; col < A[0].length; col++) {
            int zeroCount = 0;
            for (int row = 0; row < A.length; row++) {
                if (A[row][col] == 0) {
                    zeroCount++;
                }
            }
            if (A.length % 2 == 0) {
                if (zeroCount >= A.length / 2) {
                    reverseCol(A, col);
                }
            } else {
                if (zeroCount > A.length / 2) {
                    reverseCol(A, col);
                }
            }
        }
        int value = 0;
        for (int row = 0; row < A.length; row++) {
            int b = 0;
            for (int col = 0; col < A[0].length; col++) {
                b += A[row][col] * Math.pow(2, A[0].length - col - 1);
            }
            value += b;
        }

        return value;
    }

    private void reverseRow(int[][] matrix, int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] = matrix[row][i] == 0 ? 1 : 0;
        }
    }

    private void reverseCol(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = matrix[i][col] == 0 ? 1 : 0;
        }
    }

    public static void main(String[] args) {
        MatrixScore score = new MatrixScore();
        Assert.assertEquals(39, score.matrixScore(new int[][]{
                {0,0,1,1},{1,0,1,0},{1,1,0,0}
        }));
    }
}
