package com.theonecai.leetcode.array;

import java.util.Arrays;

/**
 * leetcode 565
 * @Author: theonecai
 * @Date: Create in 2021/2/17 09:27
 * @Description:
 */
public class MatrixReshape {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        int col = nums[0].length;
        int len = row * col;
        if (len != r * c) {
            return nums;
        }
        int[][] matrix = new int[r][c];
        int i = 0;
        int j = 0;
        while (i < len) {
            matrix[j / c][j % c] = nums[i / col][i % col];
            j++;
            i++;
        }

        return matrix;
    }

    public static void main(String[] args) {
        MatrixReshape reshape = new MatrixReshape();
        int[][] matrix = reshape.matrixReshape(new int[][]{{1,2},{3,4}}, 2, 3);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
