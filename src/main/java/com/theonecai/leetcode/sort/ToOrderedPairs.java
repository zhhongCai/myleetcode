package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;
import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 342
 * @Author: theonecai
 * @Date: Create in 2020/9/2 20:25
 * @Description:
 */
public class ToOrderedPairs {

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        Arrays.sort(nums);
        toOrderedPairs(nums);
    }

    private void toOrderedPairs(int[] nums) {
        int[] result = new int[nums.length];
        int mid = (nums.length + 1) / 2;
        for (int i = 0, odd = mid - 1, event = nums.length - 1 ; i < nums.length; i += 2, odd--, event--) {
            result[i] = nums[odd];
            if (i + 1 < nums.length) {
                result[i + 1] = nums[event];
            }
        }

        System.arraycopy(result, 0, nums, 0, result.length);
    }

    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        dividedAndConquer(nums, 0, nums.length - 1);
    }

    public void dividedAndConquer(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }

        int mid = (end + start) / 2;
        dividedAndConquer(nums, start, mid);
        dividedAndConquer(nums, mid + 1, end);

        merge(nums, start, mid, end);
    }

    private void merge(int[] nums, int start, int mid, int end) {
        int leftIndex = start;
        int rightIndex = mid + 1;
        int[] tmpArray;
        if (end - start + 1 < nums.length) {
            int tmpIndex = 0;
            tmpArray = new int[end - start + 1];
            while (leftIndex <= mid || rightIndex <= end) {
                if (rightIndex > end || (leftIndex <= mid && nums[leftIndex] < nums[rightIndex])) {
                    tmpArray[tmpIndex++] = nums[leftIndex++];
                } else {
                    tmpArray[tmpIndex++] = nums[rightIndex++];
                }
            }

            System.arraycopy(tmpArray, 0, nums, start, tmpArray.length);
            return;
        }

        toOrderedPairs(nums);
    }

    public static void main(String[] args) {
        ToOrderedPairs toOrderedPairs = new ToOrderedPairs();
        int[] nums = {4,5,5,6};
        toOrderedPairs.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
        checkOrder(nums);
        toOrderedPairs.wiggleSort2(nums);
        checkOrder(nums);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {0, 4, 0, 10, 7, 8, 0, 4, 10, 13, 8, 7, 7, 14, 1};
        toOrderedPairs.wiggleSort(nums2);
        checkOrder(nums2);
        toOrderedPairs.wiggleSort2(nums2);
        checkOrder(nums2);

        int[] nums3 = {1, 1, 1, 1, 2, 3, 4, 2};
        toOrderedPairs.wiggleSort(nums3);
        checkOrder(nums3);
        toOrderedPairs.wiggleSort2(nums3);
        checkOrder(nums3);

        int[] nums4 = {0, 1, 2, 1, 2};
        toOrderedPairs.wiggleSort(nums4);
        checkOrder(nums4);
        toOrderedPairs.wiggleSort2(nums4);
        checkOrder(nums4);

        int[] nums42 = {1,1,1,1,1,3,3,3,3,3};
        toOrderedPairs.wiggleSort(nums42);
        checkOrder(nums42);
        toOrderedPairs.wiggleSort2(nums42);
        checkOrder(nums42);

        int[] nums43 = {1,1,1,1,1,3,3,3,3,3,2};
        toOrderedPairs.wiggleSort(nums43);
        checkOrder(nums43);
        toOrderedPairs.wiggleSort2(nums43);
        checkOrder(nums43);


        int[] nums5 = ArrayUtil.randIntArray(1000000);
        RunUtil.runAndPrintCostTime(() -> toOrderedPairs.wiggleSort(nums5));
        RunUtil.runAndPrintCostTime(() -> toOrderedPairs.wiggleSort(nums5));

    }

    private static void checkOrder(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                if (i > 0) {
                    Assert.assertTrue(nums[i] < nums[i - 1]);
                }
                if (i + 1 < nums.length) {
                    Assert.assertTrue(nums[i] < nums[i + 1]);
                }
            } else {
                if (i + 1 < nums.length) {
                    Assert.assertTrue(nums[i] > nums[i + 1]);
                }
                Assert.assertTrue(nums[i] > nums[i - 1]);
            }
        }
    }
}
