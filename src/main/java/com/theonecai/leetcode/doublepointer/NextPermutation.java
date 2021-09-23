package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

/**
 * leetcode 31
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int left = - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                left = i;
                break;
            }
        }
        if (left == -1) {
            reversed(nums, 0, n - 1);
            return;
        }

        int right = n - 1;
        while (nums[right] <= nums[left]) {
            right--;
        }
        swap(nums, left, right);

        reversed(nums, left + 1, n - 1);
    }

    private void reversed(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();
        int[] nums = new int[]{1,3,2};
        nextPermutation.nextPermutation(nums);
        Assert.assertArrayEquals(new int[]{2,1,3}, nums);
        int[] nums2 = new int[]{3, 2, 1};
        nextPermutation.nextPermutation(nums2);
        Assert.assertArrayEquals(new int[]{1,2,3}, nums2);

    }
}
