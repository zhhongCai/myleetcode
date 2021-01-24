package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 * leetcode 674
 * @Author: theonecai
 * @Date: Create in 2021/1/24 09:55
 * @Description:
 */
public class FindLengthOfLCIS {

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = 1;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            while (j < nums.length && nums[j] > nums[j - 1]) {
                j++;
            }
            len = Math.max(len, j - i);
            i = j - 1;
        }

        return len;
    }

    public static void main(String[] args) {
        FindLengthOfLCIS findLengthOfLCIS = new FindLengthOfLCIS();
        Assert.assertEquals(3, findLengthOfLCIS.findLengthOfLCIS(new int[]{1,3,5,4,7}));
        Assert.assertEquals(1, findLengthOfLCIS.findLengthOfLCIS(new int[]{2,2,2,2,2}));
        Assert.assertEquals(1, findLengthOfLCIS.findLengthOfLCIS(new int[]{2}));
        Assert.assertEquals(0, findLengthOfLCIS.findLengthOfLCIS(new int[]{}));
    }
}
