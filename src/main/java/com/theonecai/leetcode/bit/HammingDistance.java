package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 477
 */
public class HammingDistance {

    /**
     * 计算每位的汉明距离
     * @param nums
     * @return
     */
    public int totalHammingDistance(int[] nums) {
        int[] oneBitCount = new int[32];
        for (int num : nums) {
            int shift = 0;
            while (num != 0) {
                int bit = num & 1;
                if (bit == 1) {
                    oneBitCount[shift]++;
                }
                num >>= 1;
                shift++;
            }
        }

        int sum = 0;
        int n = nums.length;
        for (int oneBits : oneBitCount) {
            //计算每位的汉明距离=此位1的个数 * (n - 此位1的个数)
            sum += oneBits * (n - oneBits);
        }
        return sum;
    }

    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        Assert.assertEquals(6, hammingDistance.totalHammingDistance(new int[]{4,14,2}));
        Assert.assertEquals(0, hammingDistance.totalHammingDistance(new int[]{4,4,4}));
    }
}
