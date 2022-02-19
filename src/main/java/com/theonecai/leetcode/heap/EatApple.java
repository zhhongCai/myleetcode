package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 1705
 */
public class EatApple {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int count = 0;
        int day = 0;
        for (; day < apples.length || !minHeap.isEmpty(); day++) {
            if (day < apples.length && apples[day] > 0) {
                minHeap.offer(new int[]{day + days[day], apples[day]});
            }
            while (!minHeap.isEmpty()) {
                int[] top = minHeap.peek();
                if (top[0] <= day) {
                    minHeap.poll();
                    continue;
                }
                count++;
                top[1]--;
                if (top[0] - 1 <= day || top[1] == 0) {
                    minHeap.poll();
                }
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        EatApple eatApple = new EatApple();
        Assert.assertEquals(7, eatApple.eatenApples(new int[]{1,2,3,5,2}, new int[]{3,2,1,4,2}));
        Assert.assertEquals(5, eatApple.eatenApples(new int[]{3,0,0,0,0,2}, new int[]{3,0,0,0,0,2}));
    }
}
