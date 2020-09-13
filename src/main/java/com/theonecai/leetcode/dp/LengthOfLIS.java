package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 300
 *
 * @Author: theonecai
 * @Date: Create in 2020/9/12 15:40
 * @Description:
 */
public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            int low = 0;
            int high = piles;
            int mid;
            while (low < high) {
                mid = (low + high) / 2;
                if (top[mid] > nums[i]) {
                    high = mid;
                } else if (top[mid] < nums[i]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            if (low == piles) {
                piles++;
            }
            top[low] = nums[i];
        }
        return piles;
    }

    public static void main(String[] args) {
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        Assert.assertEquals(4, lengthOfLIS.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
