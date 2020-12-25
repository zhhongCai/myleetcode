package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 1131
 */
public class MaxAbsValExpr {

    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int[][] direction = new int[][] {
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
        };
        int result = Integer.MIN_VALUE;
        for (int j = 0; j < direction.length; j++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < arr1.length; i++) {
                min = Math.min(min, arr1[i] * direction[j][0] - arr2[i] * direction[j][1] + i);
                max = Math.max(max, arr1[i] * direction[j][0] - arr2[i] * direction[j][1] + i);
            }
            result = Math.max(result, max - min);
        }
        return result;
    }

    public static void main(String[] args) {
        MaxAbsValExpr maxAbsValExpr = new MaxAbsValExpr();
        Assert.assertEquals(6, maxAbsValExpr.maxAbsValExpr(new int[]{1,2,3}, new int[]{1,2,3}));
    }
}
