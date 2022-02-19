package com.theonecai.leetcode.binarysearch;

/**
 * 1eetcode 33
 */
public class Search33 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
            } else {

            }
        }
        return -1;
    }
}
