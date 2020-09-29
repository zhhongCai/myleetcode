package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 910
 * @Author: theonecai
 * @Date: Create in 2020/9/28 20:10
 * @Description:
 */
public class SmallestRangeII {

    public int smallestRangeII(int[] A, int K){
        Arrays.sort(A);

        int res = A[A.length - 1] - A[0];
        int min = 0;
        int max = 0;
        for (int i = 0; i < A.length - 1; i++) {
            min = Math.min(A[0] + K, A[i + 1] - K);
            max = Math.max(A[A.length - 1] - K, A[i] + K);
            res = Math.min(res, max - min);
        }
        return res;
    }

    public static void main(String[] args) {
        SmallestRangeII smallestRangeII = new SmallestRangeII();
        Assert.assertEquals(3, smallestRangeII.smallestRangeII(new int[]{2,7,2}, 1));
        Assert.assertEquals(4, smallestRangeII.smallestRangeII(new int[]{1,7,8,8}, 5));
        Assert.assertEquals(1, smallestRangeII.smallestRangeII(new int[]{7,8,8}, 5));
        Assert.assertEquals(3, smallestRangeII.smallestRangeII(new int[]{1,3,6}, 3));
        Assert.assertEquals(6, smallestRangeII.smallestRangeII(new int[]{0,10}, 2));
    }

}
