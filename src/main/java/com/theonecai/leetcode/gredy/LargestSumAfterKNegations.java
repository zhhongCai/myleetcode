package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 1005
 */
public class LargestSumAfterKNegations {

    public int largestSumAfterKNegations(int[] nums, int k) {
        int[] count = new int[201];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            count[nums[i] + 100]++;
        }
        for (int i = 0; i < 100 && k > 0; i++) {
            while (count[i] > 0 && k > 0) {
                int kk = Math.min(k, count[i]);
                k -= kk;
                count[i] -= kk;
                count[200 - i] += kk;
            }
        }
        for (int i = 100; i < count.length && k > 0; i++) {
            if (count[i] > 0) {
                if (k % 2 == 1) {
                    count[i]--;
                    count[200 - i]++;
                }
                break;
            }
        }
        int sum = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                sum += (i - 100) * count[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        LargestSumAfterKNegations kNegations = new LargestSumAfterKNegations();
        Assert.assertEquals(6, kNegations.largestSumAfterKNegations(new int[]{3,-1,0,2}, 3));
        Assert.assertEquals(5, kNegations.largestSumAfterKNegations(new int[]{4,2,3}, 1));

        Assert.assertEquals(13, kNegations.largestSumAfterKNegations(new int[]{2,-3,-1,5,-4}, 2));
    }
}
