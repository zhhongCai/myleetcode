package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 871
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/13 21:31
 * @Description:
 */
public class MinRefuelStops {

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        // 不需要加油
        if (startFuel >= target) {
            return 0;
        }
        // 没有加油站
        if (stations == null || stations.length == 0) {
            return -1;
        }
        // 到不了第一个加油站
        if (startFuel < stations[0][0]) {
            return -1;
        }

        // 根据加油站的油量构造最大堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(stations.length, Comparator.reverseOrder());
        // 加油次数
        int count = 0;
        // 当前累计油量
        int fuelSum = startFuel;
        for (int i = 0; i < stations.length; i++) {
            // 当前累计油量可以到加油站i
            if (fuelSum >= stations[i][0]) {
                maxHeap.add(stations[i][1]);
            } else {
                // 当前累计油量到不了加油站i，取前面经过但还没加过油的油量最大的加油站加油
                if (maxHeap.isEmpty()) {
                    return -1;
                }
                fuelSum += maxHeap.poll();
                count++;
                // 加完油后，再处理加油站i
                i--;
            }
            // 当前累计油量可达目的地退出
            if (fuelSum >= target) {
                break;
            }
        }

        while (fuelSum < target && !maxHeap.isEmpty()) {
            fuelSum += maxHeap.poll();
            count++;
        }


        return fuelSum >= target ? count : -1;
    }

    public static void main(String[] args) {
        MinRefuelStops minRefuelStops = new MinRefuelStops();
        int[][] stations = {
                {10, 80},
                {20, 90},
                {30, 70},
                {60, 40},
        };
        Assert.assertEquals(1, minRefuelStops.minRefuelStops(100, 20, stations));
    }
}
