package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @Author: theonecai
 * @Date: Create in 2021/03/20 10:24
 * @Description:
 */
public class Weekend233 {


    public int maxAscendingSum(int[] nums) {
        int max = Integer.MIN_VALUE;
        int s = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                max = Math.max(max, s);
                s = nums[i];
            } else {
                s += nums[i];
            }
        }
        max = Math.max(max, s);

        return max;
    }

    public int getNumberOfBacklogOrders(int[][] orders) {
        int mod = 1000000007;

        PriorityQueue<int[]> buyMaxHeap = new PriorityQueue<>((a, b) ->  b[0] - a[0]);
        PriorityQueue<int[]> sellMinHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int[] order : orders) {
            int remain = order[1];
            // buy
            if (order[2] == 0) {
                while (!sellMinHeap.isEmpty() && sellMinHeap.peek()[0] <= order[0] && remain > 0) {
                    int[] sell = sellMinHeap.poll();
                    if (remain >= sell[1]) {
                        remain -= sell[1];
                    } else {
                        sell[1] -= remain;
                        remain = 0;
                        sellMinHeap.add(sell);
                    }
                }
                if (remain > 0) {
                    order[1] = remain;
                    buyMaxHeap.add(order);
                }
            } else {
                // sell
                while (!buyMaxHeap.isEmpty() && buyMaxHeap.peek()[0] >= order[0] && remain > 0) {
                    int[] buy = buyMaxHeap.poll();
                    if (remain >= buy[1]) {
                        remain -= buy[1];
                    } else {
                        buy[1] -= remain;
                        remain = 0;
                        buyMaxHeap.add(buy);
                    }
                }
                if (remain > 0) {
                    order[1] = remain;
                    sellMinHeap.add(order);
                }
            }
        }
        long result = 0;
        while (!buyMaxHeap.isEmpty()) {
            result += buyMaxHeap.poll()[1];
            result %= mod;
        }
        while (!sellMinHeap.isEmpty()) {
            result += sellMinHeap.poll()[1];
            result %= mod;
        }

        result %= mod;
        return (int)result;
    }

    public int maxValue(int n, int index, int maxSum) {
        int low = 1;
        int high = maxSum;
        while (low < high) {
            int mid = (high + low + 1) /2;
            if (check(mid, n, index, maxSum)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * 考虑idnex左边递减，右边递减的情况
     * @param mid
     * @param n
     * @param index
     * @param maxSum
     * @return
     */
    private boolean check(int mid, int n, int index, int maxSum) {
        long sum = mid;
        // 1 2 3 2 1
        if (index > 0) {
            sum += sumRange(0, index - 1, mid - 1);
        }
        if (index < n - 1) {
            sum += sumRange(index + 1, n - 1, mid - 1);
        }
        return sum <= maxSum;
    }

    private long sumRange(int from, int to, int num) {
        if (num == 0) {
            return 0;
        }
        long sum = 0;
        int n = to - from + 1;
        if (num > n) {
            sum += (long)num * (num + 1) / 2 - (long)(num - n) * (num - n + 1) / 2;
        } else {
            sum += (long)num * (num + 1) / 2 + n - num;
        }
        return sum;
    }


    public static void main(String[] args) {
        Weekend233 weekend = new Weekend233();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        Assert.assertEquals(155230825, this.maxValue(6,2,931384943));
        Assert.assertEquals(271698267, this.maxValue(3,0, 815094800));
        Assert.assertEquals(2, this.maxValue(4, 2, 6));
        Assert.assertEquals(3, this.maxValue(6, 1, 10));
    }

    private void test2() {
        Assert.assertEquals(34, this.getNumberOfBacklogOrders(new int[][]{
                {26,7,0},{16,1,1},{14,20,0},{23,15,1},{24,26,0},{19,4,1},{1,1,0},
        }));
        Assert.assertEquals(6, this.getNumberOfBacklogOrders(new int[][]{
                {10,5,0},{15,2,1},{25,1,1},{30,4,0},
        }));
        Assert.assertEquals(999999984, this.getNumberOfBacklogOrders(new int[][]{
                {7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1},
        }));
    }

    private void test() {
        Assert.assertEquals(65, this.maxAscendingSum(new int[]{10,20,30,5,10,50}));
        Assert.assertEquals(150, this.maxAscendingSum(new int[]{10,20,30,40,50}));
        Assert.assertEquals(33, this.maxAscendingSum(new int[]{12,17,15,13,10,11,12}));
        Assert.assertEquals(100, this.maxAscendingSum(new int[]{100,10,1}));
    }
}
