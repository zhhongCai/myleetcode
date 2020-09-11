package com.theonecai.leetcode.sort;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

/**
 * leetcode 1353
 * @Author: theonecai
 * @Date: Create in 2020/9/8 20:06
 * @Description:
 */
public class MeetingCount {

    public int maxEvents(int[][] events) {
        if (events == null || events.length == 0) {
            return 0;
        }
        // 时间短的先
        Arrays.sort(events, ((o1, o2) -> {
            int res = o1[0] - o2[0];
            if (res == 0) {
                return o1[1] - o2[1];
            }
            return res;
        }));

        int count = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int i = 0;
        int last = 1;
        while (i < events.length || !minHeap.isEmpty()) {
            while (i < events.length && events[i][0] == last) {
                if (last <= events[i][1]) {
                    minHeap.add(events[i][1]);
                }
                i++;
            }
            while (!minHeap.isEmpty() && last > minHeap.peek()) {
                minHeap.poll();
            }
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                count++;
            }
            last++;
        }

        return count;
    }


    public static void main(String[] args) {
        MeetingCount meetingCount = new MeetingCount();
        int[][] meeting = {
            {1, 2},
            {1, 3},
            {1, 2},
            {1, 2},
            {2, 4},
            {7, 8},
            {7, 9},
            {8, 9},
        };
        Assert.assertEquals(7, meetingCount.maxEvents(meeting));
        Assert.assertEquals(4, meetingCount.maxEvents(new int[][]{
                {1,4},
                {4, 4},
                {2,2},
                {3,4},
                {1,1}}));
        Assert.assertEquals(4, meetingCount.maxEvents(new int[][]{
                {1,2},
                {1, 5},
                {2,2},
                {4,4}}));
        Assert.assertEquals(4, meetingCount.maxEvents(new int[][]{
                {3,5},
                {1, 5},
                {3,3},
                {1,4}}));
        int[][] events = new int[100000][2];
        Random random = new Random(100000);
        for (int i = 0; i < events.length; i++) {
            int a = random.nextInt(100000);
            int b = random.nextInt(100000);
            events[i][0] = Math.min(a, b);
            events[i][1] = Math.max(a, b);
        }
        RunUtil.runAndPrintCostTime(() -> {
            Assert.assertEquals(events.length, meetingCount.maxEvents(events));
        });
    }
}
