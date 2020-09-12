package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * leetcode 1338
 * @Author: theonecai
 * @Date: Create in 2020/9/8 21:13
 * @Description:
 */
public class TeamWorkWorth {

    private static final long MOD = 1000000007L;

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        Worker[] workers = new Worker[speed.length];
        for (int i = 0; i < speed.length; i++) {
            workers[i] = new Worker(speed[i], efficiency[i]);
        }

        Arrays.sort(workers);

        long max = 0;
        long currentSum = 0;
        PriorityQueue<Integer> speedMinHeap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            currentSum += workers[i].speed;
            speedMinHeap.add(workers[i].speed);
            max = Math.max(max, currentSum * workers[i].efficient);
            if (speedMinHeap.size() == k) {
                int minSpeed = speedMinHeap.poll();
                currentSum -= minSpeed;
            }
        }

        return (int)(max % MOD);
    }

    static class Worker implements Comparable<Worker> {
        int speed;
        int efficient;

        public Worker(int speed, int efficient) {
            this.speed = speed;
            this.efficient = efficient;
        }

        @Override
        public int compareTo(Worker o) {
            int res = o.efficient - this.efficient;
            if (res == 0) {
                return o.speed - this.speed;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        TeamWorkWorth teamWorkWorth = new TeamWorkWorth();
        int[] speed = {2,10,3,1,5,8};
        int[] efficient = {5,4,3,9,7,2};
        Assert.assertEquals(60, teamWorkWorth.maxPerformance(6, speed, efficient, 2));
        Assert.assertEquals(68, teamWorkWorth.maxPerformance(6,
                new int[]{2,10,3,1,5,8},
                new int[]{5,4,3,9,7,2}, 3));
        Assert.assertEquals(72, teamWorkWorth.maxPerformance(6,
                new int[]{2,10,3,1,5,8},
                new int[]{5,4,3,9,7,2}, 4));
        Assert.assertEquals(56, teamWorkWorth.maxPerformance(3,
                new int[]{2,8,2},
                new int[]{2,7,1}, 2));
    }
}
