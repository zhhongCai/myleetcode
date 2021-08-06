package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 1337
 * @Author: theonecai
 * @Date: Create in 8/1/21 10:17
 * @Description:
 */
public class KWeakestRows {

    public int[] kWeakestRows(int[][] mat, int k) {
        int[][] counts = new int[mat.length][2];
        int c = 0;
        for (int i = 0; i < mat.length; i++) {
            c = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    c++;
                }
            }
            counts[i][0] = i;
            counts[i][1] = c;
        }

        Arrays.sort(counts, (a, b) -> {
            int r = a[1] - b[1];
            if (r == 0) {
                return a[0] - b[0];
            }
            return r;
        });

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = counts[i][0];
        }
        return res;
    }

    public static void main(String[] args) {
        KWeakestRows kWeakestRows = new KWeakestRows();
        Assert.assertArrayEquals(new int[]{2,0,3}, kWeakestRows.kWeakestRows(new int[][]{
                {1,1,0,0,0},
                {1,1,1,1,0},
                {1,0,0,0,0},
                {1,1,0,0,0},
                {1,1,1,1,1}
        }, 3));
    }
}
