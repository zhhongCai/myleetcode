package com.theonecai.leetcode.binarysearch;

import org.junit.Assert;

/**
 * leetcode 153
 * @Author: theonecai
 * @Date: Create in 2021/4/8 22:14
 * @Description:
 */
public class FindMin {

    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[mid];
    }

    public static void main(String[] args) {
        FindMin findMin = new FindMin();
        Assert.assertEquals(1, findMin.findMin(new int[]{3,2,1}));
        Assert.assertEquals(1, findMin.findMin(new int[]{2,1}));
        Assert.assertEquals(1, findMin.findMin(new int[]{1,2}));
        Assert.assertEquals(1, findMin.findMin(new int[]{3,4,5,1,2}));
        Assert.assertEquals(11, findMin.findMin(new int[]{11,13,15,17}));
        Assert.assertEquals(0, findMin.findMin(new int[]{4,5,6,7,0,1,2}));
        Assert.assertEquals(0, findMin.findMin(new int[]{0,1,2,3,4,5}));
        Assert.assertEquals(0, findMin.findMin(new int[]{4,5,0,1,2,3}));
        Assert.assertEquals(0, findMin.findMin(new int[]{4,5,7,8,0,1,2,3}));
    }
}
