package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 * leetcode 1052
 * @Author: theonecai
 * @Date: Create in 2021/2/23 22:24
 * @Description:
 */
public class MaxSatisfied {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int[] preSum = new int[customers.length];
        int[] grumpyPreSum = new int[customers.length];
        preSum[0] = customers[0];
        grumpyPreSum[0] = grumpy[0] == 1 ? 0 : customers[0];
        for (int i = 1; i < customers.length; i++) {
            preSum[i] = preSum[i - 1] + customers[i];
            grumpyPreSum[i] = grumpyPreSum[i - 1] + (grumpy[i] == 1 ?  0 : customers[i]);
        }
        int right = X - 1;
        int max = right >= 0 ? (preSum[right] + grumpyPreSum[customers.length - 1] - grumpyPreSum[right])
                : grumpyPreSum[customers.length - 1];
        while (right >= 0 && ++right < customers.length) {
            int left = right - X;
            max = Math.max(max, grumpyPreSum[left] + (preSum[right] - preSum[left]) +
                    (grumpyPreSum[customers.length - 1] - grumpyPreSum[right]));

        }

        return max;
    }

    public static void main(String[] args) {
        MaxSatisfied maxSatisfied = new MaxSatisfied();
        Assert.assertEquals(24, maxSatisfied.maxSatisfied(new int[]{4,10,10}, new int[]{1,1,0}, 2));
        Assert.assertEquals(0, maxSatisfied.maxSatisfied(new int[]{1}, new int[]{1}, 0));
        Assert.assertEquals(1, maxSatisfied.maxSatisfied(new int[]{1}, new int[]{1}, 1));
        Assert.assertEquals(6, maxSatisfied.maxSatisfied(new int[]{1,2,3}, new int[]{0,1,0}, 1));
        Assert.assertEquals(15, maxSatisfied.maxSatisfied(new int[]{1,2,3,4,5}, new int[]{0,1,0,1,0}, 3));
        Assert.assertEquals(13, maxSatisfied.maxSatisfied(new int[]{1,2,3,4,5}, new int[]{0,1,0,1,0}, 2));
    }
}
