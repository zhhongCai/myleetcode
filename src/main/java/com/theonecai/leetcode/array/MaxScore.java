package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 * leetcode 1423
 * @Author: theonecai
 * @Date: Create in 2021/2/6 13:59
 * @Description:
 */
public class MaxScore {

    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int[] preSum = new int[len];
        preSum[0] = cardPoints[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + cardPoints[i];
        }
        if (k == len) {
            return preSum[len - 1];
        }

        int max = Math.max(preSum[k - 1], preSum[len - 1] - preSum[len - k - 1]);
        for (int i = 0; i < k; i++) {
            int sum = preSum[i] + preSum[len - 1] - preSum[len - (k - i - 1) - 1];
            max = Math.max(max, sum);
        }

        return max;
    }

    public static void main(String[] args) {
        MaxScore maxScore = new MaxScore();
        Assert.assertEquals(12, maxScore.maxScore(new int[]{1,2,3,4,5,6,1}, 3));
        Assert.assertEquals(4, maxScore.maxScore(new int[]{2,2,2}, 2));
        Assert.assertEquals(55, maxScore.maxScore(new int[]{9,7,7,9,7,7,9}, 7));
        Assert.assertEquals(1, maxScore.maxScore(new int[]{1,1000,1}, 1));
        Assert.assertEquals(202, maxScore.maxScore(new int[]{1,79,80,1,1,1,200,1}, 3));
        Assert.assertEquals(202, maxScore.maxScore(new int[]{1,79,80,1,1,200,1,1}, 3));
        Assert.assertEquals(1, maxScore.maxScore(new int[]{1,79,80,1,1,200,1,1}, 1));
    }
}
