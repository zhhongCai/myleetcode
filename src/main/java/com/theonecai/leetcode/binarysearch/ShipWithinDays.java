package com.theonecai.leetcode.binarysearch;

import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 4/28/21 21:03
 * @Description:
 */
public class ShipWithinDays {

    public int shipWithinDays(int[] weights, int D) {
        int max = weights[0];
        int sum = 0;
        for (int weight : weights) {
            max = Math.max(max, weight);
            sum += weight;
        }
        int left = max;
        int right = sum;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countDay(weights, mid);
            if (count <= D) {
                right = mid;
            } else{
                left = mid + 1;
            }
        }

        return left;
    }

    private int countDay(int[] weights, int capacity) {
        int count = 1;
        int current = 0;
        for (int weight : weights) {
            if (current + weight > capacity) {
                count++;
                current = 0;
            }
            current += weight;
        }
        return count;
    }

    public static void main(String[] args) {
        ShipWithinDays shipWithinDays = new ShipWithinDays();
        Assert.assertEquals(15, shipWithinDays.shipWithinDays(new int[]{10,5}, 1));
        Assert.assertEquals(10, shipWithinDays.shipWithinDays(new int[]{10}, 1));
        Assert.assertEquals(15, shipWithinDays.shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
        Assert.assertEquals(6, shipWithinDays.shipWithinDays(new int[]{3,2,2,4,1,4}, 3));
        Assert.assertEquals(3, shipWithinDays.shipWithinDays(new int[]{1,2,3,1,1}, 4));
    }
}
