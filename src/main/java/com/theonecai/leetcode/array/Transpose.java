package com.theonecai.leetcode.array;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2021/2/25 22:23
 * @Description:
 */
public class Transpose {
    public int[][] transpose(int[][] matrix) {
        int[][] tranMatrix = new int[matrix[0].length][matrix.length];
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                tranMatrix[j][i] = matrix[i][j];
            }
        }

        return tranMatrix;
    }

    public static void main(String[] args) {
        Transpose transpose = new Transpose();
        int[][] result = transpose.transpose(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
        });
        /**
         * 1 4
         * 2 5
         * 3 6
         */
        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }
        result = transpose.transpose(new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        });
        /**
         * 1 4 7
         * 2 5 8
         * 3 6 9
         */
        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
