package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1074
 * @Author: theonecai
 * @Date: Create in 5/29/21 22:18
 * @Description:
 */
public class NumSubmatrixSumTarget {

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int res = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n];
            for (int j = i; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    sum[k] += matrix[j][k];
                }
                res += subarraySum(sum, target);
            }
        }

        return res;
    }

    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int pre = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);
        for (int num : nums) {
            pre += num;
            Integer c = countMap.get(pre - k);
            if (c != null) {
                res += c;
            }
            countMap.put(pre, countMap.getOrDefault(pre, 0) + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        NumSubmatrixSumTarget submatrixSumTarget = new NumSubmatrixSumTarget();
        Assert.assertEquals(0, submatrixSumTarget.numSubmatrixSumTarget(new int[][]{
                {10}
        }, 0));
        Assert.assertEquals(4, submatrixSumTarget.numSubmatrixSumTarget(new int[][]{
                {0,1,0},{1,1,1},{0,1,0}
        }, 0));
        Assert.assertEquals(5, submatrixSumTarget.numSubmatrixSumTarget(new int[][]{
                {01,-1},{-1,1}
        }, 0));
    }
}
