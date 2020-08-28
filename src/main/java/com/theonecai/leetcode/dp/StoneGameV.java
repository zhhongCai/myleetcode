package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/24 18:52
 * @Description:
 */
public class StoneGameV {

    public int stoneGameV(int[] stoneValue) {
        if (stoneValue.length == 1) {
            return 0;
        }

        long[] sums = new long[stoneValue.length];
        int j = 1;
        sums[0] = stoneValue[0];
        for (int i = 1; i < stoneValue.length; i++) {
            sums[j] = sums[j - 1] + stoneValue[i];
            j++;
        }
        int[][] results = new int[stoneValue.length][stoneValue.length];

        int result = divide(sums, 0, stoneValue.length - 1, results);
        return result;
    }

    private int divide(long[] sums, int start, int end, int[][] results) {
        if (end <= start) {
            return 0;
        }
        if (results[start][end] != 0) {
            return results[start][end];
        }
        long before = start > 0 ? sums[start - 1] : 0;
        if (start + 1 == end) {
            return (int)Math.min(sums[start] - before, sums[end] - sums[start]);
        }

        int leftSum = 0;
        int rightSum = 0;
        int mid = 0;
        int result = 0;
        for (int i = start; i < end; i++) {
            mid = i;
            leftSum = (int)(sums[mid] - before);
            rightSum = (int)(sums[end] - sums[mid]);
            int sum = Math.min(leftSum, rightSum);
            if (leftSum > rightSum) {
                sum += divide(sums, mid + 1, end, results);
            } else if (leftSum < rightSum){
                sum += divide(sums, start, mid, results);
            } else {
                sum += Math.max(divide(sums, start, mid, results),
                        divide(sums, mid + 1, end, results));
            }
            result = Math.max(sum, result);
        }
        results[start][end] = result;

        return result;
    }

    public static void main(String[] args) {
        StoneGameV stoneGameV = new StoneGameV();
        int[] stone = {6,2,3,4,5,5};
        Assert.assertEquals(18, stoneGameV.stoneGameV(stone));

        int[] stone2 = {7,7,7,7,7,7,7};
        Assert.assertEquals(28, stoneGameV.stoneGameV(stone2));

        int[] stone3 = {9,8,2,4,6,3,5,1,7};
        Assert.assertEquals(34, stoneGameV.stoneGameV(stone3));

        int[] stone4 = {2,1,1};
        Assert.assertEquals(3, stoneGameV.stoneGameV(stone4));

        int[] stone5 = {10,9,8,
                7,6,
                5,4,
                3,
                2,1};
        Assert.assertEquals(37, stoneGameV.stoneGameV(stone5));


        int[] stone6 = {3,2,1,4};
        Assert.assertEquals(7, stoneGameV.stoneGameV(stone6));

        /**
         * 68 75 25 50 =  218
         * 212
         *
         * 34 29 77 1 2 69
         *
         *
         */
        int[] stone7 = {68,75,25,50,34,29,77,1,2,69};
        Assert.assertEquals(304, stoneGameV.stoneGameV(stone7));

        int[] stone8 = {98,77,24,49,6,12,2,44,51,96};
        Assert.assertEquals(330, stoneGameV.stoneGameV(stone8));

        int[] stone9 = {35001,57669,113475,122475,216967,222154,299195,427007,576923,729179,805161,819971,871784,880988,905205,914661,919525,930853};
        Assert.assertEquals(7926980, stoneGameV.stoneGameV(stone9));
    }
}
