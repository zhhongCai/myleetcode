package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

/**
 * leetcode 88
 */
public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (idx >= 0) {
            if (j < 0 || (i >= 0 && nums1[i] > nums2[j])) {
                nums1[idx--] = nums1[i--];
            } else {
                nums1[idx--] = nums2[j--];
            }
        }
    }

    public static void main(String[] args) {
        Merge merge = new Merge();
        int[] nums = new int[]{0};
        merge.merge(nums,0, new int[]{2}, 1);
        Assert.assertArrayEquals(new int[]{2}, nums);
    }
}
