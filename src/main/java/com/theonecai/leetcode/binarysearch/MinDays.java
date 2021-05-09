package com.theonecai.leetcode.binarysearch;

import org.junit.Assert;

/**
 * leetcode 1482
 * @Author: theonecai
 * @Date: Create in 5/9/21 09:55
 * @Description:
 */
public class MinDays {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (n < m * k) {
            return -1;
        }
        int max = 0;
        for (int day : bloomDay) {
            max = Math.max(max, day);
        }
        if (n == m * k) {
            return max;
        }
        int left = 1;
        int right = max;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean check(int[] bloomDay, int m, int k, int days) {
        int count = 0;
        int current = 0;
        for (int d : bloomDay) {
            if (d <= days) {
                current++;
                if (current == k) {
                    count++;
                    current = 0;
                }
            } else {
                current = 0;
            }
            if (count >= m) {
                return true;
            }
        }

        return count >= m;
    }

    public static void main(String[] args) {
        MinDays minDays = new MinDays();
        Assert.assertEquals(10, minDays.minDays(new int[]{1,10,3}, 3, 1));
        Assert.assertEquals(3, minDays.minDays(new int[]{1,10,3,10,2}, 3, 1));
        Assert.assertEquals(-1, minDays.minDays(new int[]{1,10,3,10,2}, 3, 2));
        Assert.assertEquals(-1, minDays.minDays(new int[]{1,10,3,10,2}, 3, 2));
        Assert.assertEquals(12, minDays.minDays(new int[]{7,7,7,7,12,7,7}, 2, 3));
        Assert.assertEquals(1000000000, minDays.minDays(new int[]{1000000000,1000000000}, 1, 1));
    }
}
