package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 1094
 * @Author: theonecai
 * @Date: Create in 2020/10/7 16:57
 * @Description:
 */
public class CarPooling {

    public boolean carPooling2(int[][] trips, int capacity) {
        Arrays.sort(trips, (Comparator.comparingInt(o -> o[1])));
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> {
            int res = o1[2] - o2[2];
            if (res == 0) {
                return o1[1] - o2[1];
            }
            return res;
        });
        int[] top = null;
        int currentCapacity = 0;
        for (int[] trip : trips) {
            top = minHeap.peek();
            while (top != null && top[2] <= trip[1]) {
                currentCapacity -= top[0];
                minHeap.poll();
                top = minHeap.peek();
            }

            if (currentCapacity + trip[0] > capacity) {
                return false;
            }

            currentCapacity += trip[0];
            minHeap.offer(trip);
        }
        return true;
    }


    public boolean carPooling(int[][] trips, int capacity) {
        /**
         * 差分数组
         * arr[0] = diff[0];
         * arr[1] = arr[0] + diff[1];
         * arr[2] = arr[1] + diff[2] = arr[0] + diff[1] + diff[2];
         * arr[i] = arr[i - 1] + diff[i];
         */
        int[] diff = new int[1001];
        int start;
        int end;
        int num;
        for (int[] trip : trips) {
            num = trip[0];
            start = trip[1];
            end = trip[2];
            diff[start] += num;
            diff[end] -= num;
        }
        // 表示在当前位置的乘客数
        int sum = 0;
        for (int d : diff) {
            sum += d;
            if (sum > capacity) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        CarPooling carPooling = new CarPooling();
        Assert.assertFalse(carPooling.carPooling(new int[][]{
                {2,1,5},
                {3,3,7},
        }, 4));

        Assert.assertTrue(carPooling.carPooling(new int[][]{
                {2,1,5},
                {3,3,7},
        }, 5));

        Assert.assertTrue(carPooling.carPooling(new int[][]{
                {2,1,5},
                {3,5,7},
        }, 3));

        Assert.assertTrue(carPooling.carPooling(new int[][]{
                {3,2,7},
                {3,7,9},
                {8,3,9},
        }, 11));
    }
}
