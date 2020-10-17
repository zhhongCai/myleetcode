package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * leetcode 1326
 * @Author: theonecai
 * @Date: Create in 2020/10/16 20:48
 * @Description:
 */
public class MinTaps {

    /**
     * 1. 获取每个水龙头的有效范围
     * 2. 按左边界排序，左边界相同，右边界大的靠前
     * 3. 从前往后，两个有重叠的范围取合并后范围最长的那两个
     * @param n
     * @param ranges
     * @return
     */
    public int minTaps(int n, int[] ranges) {
         // 水龙头的有效范围
        int[][] tapRange = new int[n + 1][2];
        for (int i = 0; i < tapRange.length; i++) {
            tapRange[i] = new int[]{
                    Math.max(0, i - ranges[i]),
                    Math.min(n, i + ranges[i])
            };
        }

        // 按左边界排序，左边界相同，右边界大的靠前
        Arrays.sort(tapRange, ((o1, o2) -> {
            int res = o1[0] - o2[0];
            if (res == 0) {
                return o2[1] - o1[1];
            }
            return res;
        }));

        int count = 1;
        int[] pre = tapRange[0];
        if (pre[0] > 0) {
            return -1;
        }
        // 右边界最大堆
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(((o1, o2) -> o2[1] - o1[1]));
        for (int i = 1; i < tapRange.length; i++) {
            if (pre[1] == n) {
                return count;
            }
            int[] current = tapRange[i];
            // 有重叠且右边界有扩展
            if (pre[0] < current[0] && current[0] <= pre[1] && current[1] > pre[1]) {
                maxHeap.offer(current);
                continue;
            }
            if (pre[1] < current[0]) {
                if (maxHeap.isEmpty()) {
                    return -1;
                }
                count++;
                pre = maxHeap.poll();
                maxHeap.clear();
                if (pre[1] < current[0]) {
                    return -1;
                }
                maxHeap.offer(current);
            }

        }

        if (pre[1] < n) {
            if (!maxHeap.isEmpty() && maxHeap.peek()[1] == n) {
                return count + 1;
            }
            return -1;
        }
        return count;
    }

    public static void main(String[] args) {
        MinTaps minTaps = new MinTaps();
        Assert.assertEquals(1, minTaps.minTaps(5, new int[]{3,4,1,1,0,0}));
        Assert.assertEquals(-1, minTaps.minTaps(3, new int[]{0,0,0,0}));
        Assert.assertEquals(3, minTaps.minTaps(7, new int[]{1,2,1,0,2,1,0,1}));
        Assert.assertEquals(2, minTaps.minTaps(8, new int[]{4,0,0,0,0,0,0,0,4}));
        Assert.assertEquals(1, minTaps.minTaps(8, new int[]{4,0,0,0,4,0,0,0,4}));
    }
}
