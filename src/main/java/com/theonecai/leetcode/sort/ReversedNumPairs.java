package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 493
 * @Author: theonecai
 * @Date: Create in 2020/8/26 21:58
 * @Description:
 */
public class ReversedNumPairs {
    /**
     * 树状数组
     * @param nums
     * @return
     */
    public int reversePairs2(int[] nums) {
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        int[] biTree = new int[sortedNums.length + 1];
        int count = 0;
        for (int num : nums) {
            int i = indexOf(sortedNums, num * 2L + 1L);
            count += getCount(biTree, biTree.length - 1) - getCount(biTree, i - 1);
            update(biTree, indexOf(sortedNums, num), 1);
        }

        return count;
    }

    /**
     * 二分查找num在有序数组sortedNums中的位置
     * @param sortedNums
     * @param num
     * @return
     */
    private int indexOf(int[] sortedNums, long num) {
        int left = 0;
        int right = sortedNums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (sortedNums[mid] > num) {
                right = mid - 1;
            } else if (sortedNums[mid] < num) {
                left = mid + 1;
            } else {
                return mid + 1;
            }
        }
        return left + 1;
    }

    private int lowbit(int n) {
        return n & (-n);
    }

    /**
     * 小于等于index位置的数的个数
     * @param biTree
     * @param index
     * @return
     */
    private int getCount(int[] biTree, int index) {
        if (index >= biTree.length) {
            return 0;
        }
        int i = index;
        int count = 0;
        while (i > 0) {
            count += biTree[i];
            i -= lowbit(i);
        }
        return count;
    }

    public void update(int[] biTree, int index, int value) {
        int i = index;
        while (i < biTree.length) {
            biTree[i] += value;
            i += lowbit(i);
        }
    }


    public int reversePairs(int[] nums) {
        int[] tmpArray = new int[nums.length];

        return mergeSort(nums, 0, nums.length - 1, tmpArray);
    }

    private int mergeSort(int[] nums, int start, int end, int[] tmpArray) {
        if (start >= end) {
            return 0;
        }

        int mid = (end + start) / 2;
        int count = mergeSort(nums, start, mid, tmpArray);
        count += mergeSort(nums, mid + 1, end, tmpArray);

        count += merge(nums, start, mid, end, tmpArray);
        return count;
    }

    private int merge(int[] nodes, int start, int mid, int end, int[] tmpArray) {
        int count = 0;
        int leftIndex = start;
        int rightIndex = mid + 1;
        int tmpIndex = start;
        int j = rightIndex;
        for (int i = start; i <= mid; i++) {
            while (j <= end && nodes[i] > nodes[j] * 2L) {
                j++;
            }
            count += j - rightIndex;
        }
        while (leftIndex <= mid || rightIndex <= end) {
            if (rightIndex > end || (leftIndex <= mid && nodes[leftIndex] < nodes[rightIndex])) {
                tmpArray[tmpIndex++] = nodes[leftIndex++];
            } else {
                tmpArray[tmpIndex++] = nodes[rightIndex++];
            }
        }

        System.arraycopy(tmpArray, start, nodes, start, end - start + 1);

        return count;
    }

    public static void main(String[] args) {
        ReversedNumPairs reversedNumPairs = new ReversedNumPairs();
        int[] nums = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        Assert.assertEquals(0, reversedNumPairs.reversePairs(nums));
        Assert.assertEquals(0, reversedNumPairs.reversePairs2(nums));

        int[] nums2 = {-5, -5};
        Assert.assertEquals(1, reversedNumPairs.reversePairs(nums2));
        Assert.assertEquals(1, reversedNumPairs.reversePairs2(nums2));
        /**
         * 2 3 4
         *
         * 1 5
         */
        int[] nums3 = {2,4,3,5,1};
        Assert.assertEquals(3, reversedNumPairs.reversePairs(nums3));
        Assert.assertEquals(3, reversedNumPairs.reversePairs2(nums3));
    }
}
