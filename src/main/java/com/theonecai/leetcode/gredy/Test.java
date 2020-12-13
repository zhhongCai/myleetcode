package com.theonecai.leetcode.gredy;

import java.util.Arrays;


public class Test {
    
    public int[][] updateMatrix(int[][] matrix) {
        int[][] direction = new int[][] {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
        };
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = matrix[i][j] == 0 ? 0 : Integer.MAX_VALUE;
            }
        }
        for (int t = 0; t < 200; t++) {    
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] != 0) {
                        result[i][j] = Math.min(getDist(matrix, direction, result, i, j), result[i][j]);
                    }
                }
            }
        }

        return result;
    }

    private int getDist(int[][] nums, int[][] direction, int[][] result, int i, int j) {
        int dist = nums.length * nums[0].length;
        for (int k = 0; k < direction.length; k++) {
            int row = i + direction[k][0];
            int col = j + direction[k][1];
            if (row >= 0 && row < nums.length && col >=0 && col < nums[0].length) {
                dist = Math.min(dist, result[row][col]);
            }
        }
        return dist + 1;
    }
    

    public static void main(String[] argcs) {
        Test test = new Test();
        int[][] nums = new int[][] {
            {1,0,1,1,0,0,1,0,0,1},
            {0,1,1,0,1,0,1,0,1,1},
            {0,0,1,0,1,0,0,1,0,0},
            {1,0,1,0,1,1,1,1,1,1},
            {0,1,0,1,1,0,0,0,0,1},
            {0,0,1,0,1,1,1,0,1,0},
            {0,1,0,1,0,1,0,0,1,1},
            {1,0,0,0,1,1,1,1,0,1},
            {1,1,1,1,1,1,1,0,1,0},
            {1,1,1,1,0,1,0,0,1,1}
        };
        int[][] result = test.updateMatrix(nums);
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }
}
