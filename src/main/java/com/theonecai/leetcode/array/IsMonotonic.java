package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 * leetcode 896
 * @Author: theonecai
 * @Date: Create in 2021/2/28 22:31
 * @Description:
 */
public class IsMonotonic {

    public boolean isMonotonic(int[] A) {
        if (A.length <= 2) {
            return true;
        }
        int result = A[0] - A[1];
        for (int i = 2; i < A.length; i++) {
            if (result == 0) {
                result = A[i - 1] - A[i];
            } else if (result > 0) {
                if (A[i] > A[i - 1]) {
                    return false;
                }
            } else {
                if (A[i] < A[i - 1]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        IsMonotonic isMonotonic = new IsMonotonic();
        Assert.assertTrue(isMonotonic.isMonotonic(new int[]{-5,-5,-5,-5,-2,-2,-2,-1,-1,-1,0}));
        Assert.assertTrue(isMonotonic.isMonotonic(new int[]{1,1,0}));
        Assert.assertTrue(isMonotonic.isMonotonic(new int[]{1,1,2}));
        Assert.assertTrue(isMonotonic.isMonotonic(new int[]{1}));
        Assert.assertTrue(isMonotonic.isMonotonic(new int[]{1,1}));
        Assert.assertTrue(isMonotonic.isMonotonic(new int[]{1,2}));
        Assert.assertTrue(isMonotonic.isMonotonic(new int[]{1,2,3}));
        Assert.assertTrue(isMonotonic.isMonotonic(new int[]{1,2,2}));
        Assert.assertTrue(isMonotonic.isMonotonic(new int[]{3,2,1}));
        Assert.assertTrue(isMonotonic.isMonotonic(new int[]{3,2,2}));
    }
}
