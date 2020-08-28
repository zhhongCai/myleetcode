package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * leetcode 56
 * @Author: theonecai
 * @Date: Create in 2020/7/1 23:37
 * @Description:
 */
public class MergeRange {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        int[][] result = new int[intervals.length][intervals[0].length];

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int index = 0;
        result[index][0] = intervals[0][0];
        result[index][1] = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= result[index][1]) {
                if (intervals[i][1] > result[index][1]) {
                    result[index][1] = intervals[i][1];
                }
            } else{
                result[++index][0] = intervals[i][0];
                result[index][1] = intervals[i][1];
            }
        }

        return Arrays.copyOf(result, index + 1);
    }

    public int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        qsort(intervals);

        List<int[]> list = new ArrayList<>(intervals.length);
        list.add(intervals[0]);
        int[] current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= current[1]) {
                if (intervals[i][1] > current[1]) {
                    current[1] = intervals[i][1];
                }
            } else{
                list.add(intervals[i]);
                current = intervals[i];
            }
        }

        return list.toArray(new int[0][]);
    }

    private void qsort(int[][] intervals) {
        qsort(intervals, 0, intervals.length - 1);
    }

    private void qsort(int[][] intervals, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = partition(intervals, start, end);
        qsort(intervals, start, p - 1);
        qsort(intervals, p + 1, end);
    }

    private int partition(int[][] intervals, int start, int end) {
        int pivot = intervals[start][0];
        int left = start;
        int right = end;
        while(left < right) {
            while (right > left && intervals[right][0] >= pivot) {
                right--;
            }
            while (left < right && intervals[left][0] <= pivot) {
                left++;
            }
            if (left < right) {
                swap(intervals, left, right);
            }
        }
        if (left != start) {
            swap(intervals, left, start);
        }

        return left;
    }

    private void swap(int[][] interval, int left, int right) {
        int[] tmp = interval[left];
        interval[left] = interval[right];
        interval[right] = tmp;
    }

    public static void main(String[] args) {
        MergeRange mergeRange = new MergeRange();
        int[][] a = {{1,3},{2,6},{8,10},{15,18}};
        int[][] result = mergeRange.merge(a);
        for (int[] r : result) {
            ArrayUtil.print(r);
        }

        result = mergeRange.merge2(a);
        for (int[] r : result) {
            ArrayUtil.print(r);
        }

//        int[][] b = {{1,4},{4,6},{8,10},{10,18}};
        int[][] b = {{1, 4}, {0,2}, {3,5}};
        result = mergeRange.merge(b);
        for (int[] r : result) {
            ArrayUtil.print(r);
        }

        result = mergeRange.merge2(b);
        for (int[] r : result) {
            ArrayUtil.print(r);
        }
    }

}
