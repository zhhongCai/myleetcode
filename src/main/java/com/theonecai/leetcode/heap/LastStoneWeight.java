package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 1046
 * @Author: theonecai
 * @Date: Create in 2020/12/30 21:14
 * @Description:
 */
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        while (!maxHeap.isEmpty()) {
            int top = maxHeap.poll();
            if (maxHeap.isEmpty()) {
                return top;
            }
            int top2 = maxHeap.poll();
            if (top > top2) {
                maxHeap.offer(top - top2);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LastStoneWeight lastStoneWeight = new LastStoneWeight();
        Assert.assertEquals(1, lastStoneWeight.lastStoneWeight(new int[]{2,7,4,1,8,1}));
    }
}
