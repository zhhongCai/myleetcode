package com.theonecai.leetcode.sort;

import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 969
 * @Author: theonecai
 * @Date: Create in 2020/9/3 20:13
 * @Description:
 */
public class RollSort {

    /**
     * 每次从未排序的nums(长度为remainLastIndex+1)中取最大值所在索引maxIndex
     * 进行两次翻转：翻转maxIndex+1，把最大值翻转到第一位，再翻转remainLastIndex+1,把最大值翻转到末尾
     * 重复直到remainLastIndex=0
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> rollIndex = new LinkedList<>();
        if (arr == null || arr.length == 1) {
            return rollIndex;
        }
        int len = arr.length;
        int maxIndex = 0;
        for (int remainLastIndex = len - 1; remainLastIndex > 0; remainLastIndex--) {
            maxIndex = 0;
            for (int j = 0; j <= remainLastIndex; j++) {
                if (arr[maxIndex] < arr[j]) {
                    maxIndex = j;
                }
            }
            if (maxIndex == remainLastIndex) {
                continue;
            }
            if (maxIndex + 1 > 1) {
                rollIndex.add(maxIndex + 1);
                swapRange(arr, 0, maxIndex);
            }
            rollIndex.add(remainLastIndex + 1);
            swapRange(arr, 0, remainLastIndex);
        }

        return rollIndex;
    }

    private void swapRange(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }
        int left = start;
        int right = end;
        int tmp;
        while (left < right) {
            tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        RollSort rollSort = new RollSort();
        int[] nums = {1,5,2,3,9,4,6,1,3,2,9};
        System.out.println(rollSort.pancakeSort(nums));

        int[] nums2 = {1,3,4};
        System.out.println(rollSort.pancakeSort(nums2));

        int[] nums3 = {5,4,3,2,1};
        System.out.println(rollSort.pancakeSort(nums3));

        int[] nums4 = {6,5,4,3,2,1};
        System.out.println(rollSort.pancakeSort(nums4));
    }
}
