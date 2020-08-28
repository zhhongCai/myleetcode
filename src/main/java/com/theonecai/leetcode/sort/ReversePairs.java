package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;

/**
 * leetcode 51
 * @Author: theonecai
 * @Date: Create in 2020/7/7 20:41
 * @Description:
 */
public class ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <2) {
            return 0;
        }

        return mergeSortCounting(nums, 0, nums.length - 1);
    }

    /**
     * 合并排序方法统计
     * @param arr
     * @param start
     * @param end : 包括end
     * @return
     */
    private int mergeSortCounting(int[] arr, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = (end + start) / 2;  // equals start + (end - start) / 2
        int sum = 0;
        sum += mergeSortCounting(arr, start, mid);
        sum += mergeSortCounting(arr, mid + 1, end);

        sum += merge(arr, start, mid, end);
        return sum;
    }

    private int merge(int[] arr, int start, int mid, int end) {
        int leftIndex = start;
        int rightIndex = mid + 1;
        int index = 0;
        int[] sortedArr = new int[end - start + 1];
        int count = 0;
        while (leftIndex <= mid && rightIndex <= end) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                sortedArr[index++] = arr[leftIndex++];
            } else {
                // 左边剩下比arr[rightIndex]大的个数（由arr[rightIndex]产生的逆序对数）
                count += mid - leftIndex + 1;
                sortedArr[index++] = arr[rightIndex++];
            }
        }
        while (leftIndex <= mid) {
            sortedArr[index++] = arr[leftIndex++];
        }
        while (rightIndex <= end) {
            sortedArr[index++] = arr[rightIndex++];
        }

        System.arraycopy(sortedArr, 0, arr, start, sortedArr.length);
        return count;
    }

    public static void main(String[] args) {
        ReversePairs rp = new ReversePairs();
        int[] arr = ArrayUtil.randIntArray(5);
        ArrayUtil.print(arr);
        System.out.println("disorder: " + rp.reversePairs(arr));
        ArrayUtil.print(arr);
        int[] b = {7,5,6,4};
        System.out.println("disorder: " + rp.reversePairs(b));
    }

}
