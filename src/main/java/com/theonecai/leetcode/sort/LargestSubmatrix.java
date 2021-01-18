package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 5655
 * @Author: theonecai
 * @Date: Create in 2021/1/17 13:55
 * @Description:
 */
public class LargestSubmatrix {

    public int largestSubmatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 求每一行的高(从0行开始连续的1的数量)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < rows; i++) {
            int[] rowHeights = matrix[i];
            Arrays.sort(rowHeights);
            for (int j = rowHeights.length - 1; j >= 0; j--) {
                count = Math.max(count, (cols - j) * rowHeights[j]);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        LargestSubmatrix largestSubmatrix = new LargestSubmatrix();
        Assert.assertEquals(4, largestSubmatrix.largestSubmatrix(new int[][]{
                {0,0,1},{1,1,1},{1,0,1}
        }));
    }
}
