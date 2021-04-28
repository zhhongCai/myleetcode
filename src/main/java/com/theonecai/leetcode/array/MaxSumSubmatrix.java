package com.theonecai.leetcode.array;

import org.junit.Assert;


/**
 * leetcode 363
 */
public class MaxSumSubmatrix {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < cols; i++) {
            int[] rowSum = new int[rows];
            for (int j = i; j < cols; j++) {
                for (int kk = 0; kk < rows; kk++) {
                    rowSum[kk] += matrix[kk][j];
                }

                ans = Math.max(ans, getMax(rowSum, k));
                if (ans == k) {
                    break;
                }
            }
        }
        return ans;
    }

    private int getMax(int[] rowSum, int k) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < rowSum.length; i++) {
            int sum = 0;
            for (int j = i; j < rowSum.length; j++) {
                sum += rowSum[j];
                if (sum <= k) {
                    ans = Math.max(ans, sum);
                }
                if (ans >= k) {
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxSumSubmatrix maxSumSubmatrix = new MaxSumSubmatrix();
        Assert.assertEquals(2, maxSumSubmatrix.maxSumSubmatrix(new int[][]{
                {1,0,1},{0,-2,3}
        },2));
        Assert.assertEquals(2, maxSumSubmatrix.maxSumSubmatrix(new int[][]{
                {2,2,-1}
        },2));
    }
}
