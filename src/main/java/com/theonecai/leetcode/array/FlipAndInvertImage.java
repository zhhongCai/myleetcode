package com.theonecai.leetcode.array;

/**
 * leetcode 832
 * @Author: theonecai
 * @Date: Create in 2021/2/24 20:38
 * @Description:
 */
public class FlipAndInvertImage {
    public int[][] flipAndInvertImage(int[][] A) {
        int[][] matrix = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = A[0].length - 1, k = 0; j >= 0; j--,k++) {
                matrix[i][k] = A[i][j] == 0 ? 1 : 0;
            }
        }

        return matrix;
    }
}
