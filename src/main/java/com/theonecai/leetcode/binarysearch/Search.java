package com.theonecai.leetcode.binarysearch;

import org.junit.Assert;

/**
 * leetcode 81
 * @Author: theonecai
 * @Date: Create in 2021/4/7 21:46
 * @Description:
 */
public class Search {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        return search(nums, 0, nums.length - 1, target);
    }

    private boolean search(int[] nums, int left, int right, int target) {
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid]) {
                left++;
                continue;
            }
            if (nums[left] < nums[mid]) {
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Search search = new Search();
        Assert.assertTrue(search.search(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1}, 2));
        Assert.assertTrue(search.search(new int[]{2,5,6,0,0,1,2}, 0));
        Assert.assertFalse(search.search(new int[]{2,5,6,0,0,1,2}, 3));
        Assert.assertFalse(search.search(new int[]{2,2,2,2,2,2,2}, 3));
    }
}
