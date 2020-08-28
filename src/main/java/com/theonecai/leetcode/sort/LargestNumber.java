package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;

/**
 * leetcode 179
 * @Author: theonecai
 * @Date: Create in 2020/6/27 09:59
 * @Description:
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        String[] strs = new String[nums.length];
        int i = 0;
        boolean allZero = true;
        for (int num : nums) {
            strs[i++] = String.valueOf(num);
            if (num != 0) {
                allZero = false;
            }
        }
        if (allZero) {
            return "0";
        }
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(strs);

        StringBuilder sb = new StringBuilder();
        for (i = strs.length - 1; i >= 0 ; i--) {
            sb.append(strs[i]);
        }

        return sb.toString();
    }

    static class QuickSort {

        public void quickSort(String[] a) {

            quickSort(a, 0, a.length - 1);
        }

        private void quickSort(String[] a, int start, int end) {
            if (start >= end) {
                return;
            }

        int pos = partition(a, start, end);

            quickSort(a, start, pos - 1);
            //+1是因为pos位置已经第pos小的数字了(有序了)
            quickSort(a, pos + 1, end);
        }

        private int partition(String[] a, int start, int end) {
            //取首元素为分区元素
            String pivot = a[start];
            // 从左往右当前比pivot大的下标,a[start~low]为比pivot小的元素
            int low = start;
            // 从右往左当前比pivot小的下标,a[hight+1~end]为比pivot大的元素
            int high = end;

            // 注意先从右往左找，后从左往右找：这样当low == high时，保证a[low]小于pivot，此处即为pivot排序后位置
            while (low < high) {
                while (high > low && compare(a[high], pivot) >= 0) {
                    high--;
                }
                while (low < high && compare(a[low], pivot) <= 0) {
                    low++;
                }
                if (low < high) {
                    swap(a, low, high);
                }
            }
            if (low != start) {
                swap(a, low, start);
            }
            return low;
        }

        private void swap(String[] a, int i, int pos) {
            if (i == pos) {
                return;
            }
            String t = a[pos];
            a[pos] = a[i];
            a[i] = t;
        }

        private int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        int[] nums = ArrayUtil.randIntArray(10);
        ArrayUtil.print(nums);
        System.out.println(largestNumber.largestNumber(nums));
        int[] a = {824,938,1399,5607,6973,5703,9609,4398,8247};
        int[] b = {824,938,1399,5607,6973,5703,9609,4398,8247,8429, 12,121};
        System.out.println(largestNumber.largestNumber(a));
        System.out.println(largestNumber.largestNumber(b));
    }

}
