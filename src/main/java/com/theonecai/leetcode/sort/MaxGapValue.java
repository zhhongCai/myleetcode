package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 164
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/25 20:24
 * @Description:
 */
public class MaxGapValue {

    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        return mergeSort(nums, 0, nums.length - 1);
    }

    public int mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        if (start == end - 1) {
            if (nums[end] < nums[start]) {
                swap(nums, start, end);
            }
            return nums[end] - nums[start];
        }

        int mid = (end + start) / 2;

        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);

        return merge(nums, start, mid, end);
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int merge(int[] nums, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int i = 0;
        int leftIndex = start;
        int rightIndex = mid + 1;
        int max = 0;
        boolean needCalculateGap = end - start + 1 == nums.length;
        while (i < tmp.length) {
            if (leftIndex <= mid) {
               if (rightIndex <= end) {
                   if (nums[leftIndex] <= nums[rightIndex]) {
                       tmp[i] = nums[leftIndex++];
                   } else {
                       tmp[i] = nums[rightIndex++];
                   }
               } else {
                   tmp[i] = nums[leftIndex++];
               }
            } else {
                tmp[i] = nums[rightIndex++];
            }
            // 最后一次的合并
            if (needCalculateGap && i > 0) {
                max = Math.max(tmp[i] - tmp[i -1], max);
            }
            i++;
        }

        System.arraycopy(tmp, 0, nums, start, tmp.length);
        return max;
    }

    public static void main(String[] args) {
        MaxGapValue maxGapValue = new MaxGapValue();
        int[] nums = {1, 1, 1, 1, 1, 1, 1};
        Assert.assertEquals(0, maxGapValue.maximumGap(nums));
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {10, 9, 8, 7, 1, 1, 0, 10,9,10, 9, 8, 7, 1, 1, 0, 10,9,10, 9, 8, 7, 1, 1, 0, 10,
                9, 9, 8, 7, 1, 1, 0, 10,9,10, 9, 8, 7, 1, 1, 0, 10,9,10, 9, 8, 7, 1, 1, 0, 10,9,
                9, 8, 7, 1, 1, 0, 10,9,10, 9, 8, 7, 1, 1, 0, 10,9,10, 9, 8, 7, 1, 1, 0, 10,9,
                9, 8, 7, 1, 1, 0, 10,9,10, 9, 8, 7, 1, 1, 0, 10,9,10, 9, 8, 7, 1, 1, 0, 10,9,
                9, 8, 7, 1, 1, 0, 10,9,10, 9, 8, 7, 1, 1, 0, 10,9,10, 9, 8, 7, 1, 1, 0, 10,9};
        Assert.assertEquals(6, maxGapValue.maximumGap(nums2));
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = {13684,13701,15157,2344,28728,16001,9900,7367,30607,5408,17186,13230,1598,9766,13083,27618,29065,
                9171,2470,20163,5530,20665,14818,4743,24871,27852,8129,4071,17488,30904,1548,16408,1734,17271,19880,
                22269,18738,30242,6679,19867,13781,4615,10049,28877,9323,5373,11381,18489,13654,14324,28843,27010,
                10232,31696,29708,3008,28769,30840,21172,28461,20522,8745,17590,27936,30368,30993,24416,17472};
        Assert.assertEquals(2147, maxGapValue.maximumGap(nums3));
        System.out.println(Arrays.toString(nums3));
    }
}
