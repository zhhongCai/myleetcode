package com.theonecai.leetcode.heap;

import com.theonecai.algorithms.ArrayUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * leetcodd 973
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/13 20:55
 * @Description:
 */
public class KClosest {

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> b[2] - a[2]);

        for (int i = 0; i < points.length; i++) {
            if (i < K) {
                int[] tmp = {points[i][0], points[i][1], powAndSum(points[i][0], points[i][1])};
                maxHeap.add(tmp);
            } else {
                int sum = powAndSum(points[i][0], points[i][1]);
                if (sum < maxHeap.peek()[2]) {
                    maxHeap.poll();
                    int[] tmp = {points[i][0], points[i][1],  sum};
                    maxHeap.add(tmp);
                }
            }
        }

        int[][] result = new int[K][2];
        int i = 0;
        int[] tmp;
        while (!maxHeap.isEmpty()) {
            tmp = maxHeap.poll();
            result[i][0] = tmp[0];
            result[i][1] = tmp[1];
            i++;
        }
        return result;
    }

    public int[][] kClosest2(int[][] points, int K) {
        int p = -1;
        int start = 0;
        int end = points.length - 1;
        while (p != K - 1) {
            p = partition(points, start, end);
            if (p < K - 1) {
                start = p + 1;
            } else if (p > K - 1){
               end = p - 1;
            }
        }

        return Arrays.copyOf(points,  K);
    }

    private int partition(int[][] points, int start, int end) {
        if (start == end) {
            return start;
        }
        //分区元素
        int pivotIndex = start;
        int pivotVal = powAndSum(points[pivotIndex][0], points[pivotIndex][1]);
        int low = start;
        int high = end;

        while (low < high) {
            while (low < high && powAndSum(points[low][0], points[low][1]) < pivotVal) {
                low++;
            }
            while (high > low && powAndSum(points[high][0], points[high][1]) >= pivotVal) {
                high--;
            }
            if (low < high) {
                if (low == pivotIndex) {
                    pivotIndex = high;
                }
                swap(points, low, high);
            }
        }
        if (low != pivotIndex) {
            swap(points, low, pivotIndex);
        }

        return low;
    }

    private void swap(int[][] points, int low, int high) {
        int[] tmp = points[low];
        points[low] = points[high];
        points[high] = tmp;
    }

    public int powAndSum(int a, int b) {
        return a * a + b * b;
    }

    public static void main(String[] args) {
        KClosest kClosest = new KClosest();
        int[][] points = {
                {1, 3},
                {-2, 2},
                {1, 2}
        };
        int[][] result = kClosest.kClosest(points, 2);
        for (int[] ints : result) {
            ArrayUtil.print(ints);
        }

        result = kClosest.kClosest2(points, 2);
        for (int[] ints : result) {
            ArrayUtil.print(ints);
        }
    }
}
