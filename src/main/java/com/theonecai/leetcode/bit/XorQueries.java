package com.theonecai.leetcode.bit;

import java.util.Arrays;

/**
 * leetcode 1310
 */
public class XorQueries {

    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] result = new int[queries.length];
        // prefixXor[i] = arr[0] ^ arr[1] ^ ... ^ arr[i - 1] ^ arr[i];
        int[] prefixXor = new int[arr.length];
        prefixXor[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
//            System.out.println(Integer.toBinaryString(arr[i]));
            prefixXor[i] = prefixXor[i - 1] ^ arr[i];
        }
//        System.out.println(Arrays.toString(prefixXor));
        for (int i = 0; i < result.length; i++) {
            result[i] = (queries[i][0] > 0 ? prefixXor[queries[i][0] - 1] : 0) ^ prefixXor[queries[i][1]];
        }
        return result;
    }

    public static void main(String[] args) {
        XorQueries xorQueries = new XorQueries();
        // [2,7,14,8]
        System.out.println(Arrays.toString(xorQueries.xorQueries(new int[]{
                1,3,4,8
        }, new int[][]{
                {0,1},{1,2},{0,3},{3,3}
        }))); 
        // [8,0,4,4]
        System.out.println(Arrays.toString(xorQueries.xorQueries(new int[]{
                4,8,2,10
        }, new int[][]{
                {2,3},{1,3},{0,0},{0,3}
        })));
    }
}
