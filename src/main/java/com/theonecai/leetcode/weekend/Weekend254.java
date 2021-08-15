package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Author: theonecai
 * @Date: Create in 2021/08/15 10:24
 * @Description:
 */
public class Weekend254 {


    public int numOfStrings(String[] patterns, String word) {

        int res = 0;
        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                res++;
            }
        }
        return res;
    }

    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] res = new int[n];
        int mid = n / 2;
        int k = 0;
        for (int j = mid, i = 0; i < mid; i++, j++) {
            res[k++] = nums[j];
            if (k < n) {
                res[k++] = nums[i];
            }
        }
        if (k < n) {
            res[k] = nums[n - 1];
        }
//        System.out.println(Arrays.toString(res));
        return res;
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[row][col];
        for (int i = 0; i < col; i++) {
            queue.add(new int[]{0,i});
            seen[0][i] = true;
        }


        return res;
    }


    public static void main(String[] args) {
        Weekend254 weekend = new Weekend254();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
        latestDayToCross(2,2, new int[][]{
                {1,1},{2,1},{1,2},{2,2}
        });
    }

    private void test3() {
    }

    private void test2() {
        Assert.assertArrayEquals(new int[]{3,1,4,2,5}, rearrangeArray(new int[] {1,2,3,4,5}));
        Assert.assertArrayEquals(new int[]{6, 0, 7, 2, 9}, rearrangeArray(new int[] {6,2,0,9,7}));
        Assert.assertArrayEquals(new int[]{10,1,12,3,13,4,14,6,17,7}, rearrangeArray(new int[] {3,1,12,10,7,6,17,14,4,13}));
    }

    private void test() {
    }
}
